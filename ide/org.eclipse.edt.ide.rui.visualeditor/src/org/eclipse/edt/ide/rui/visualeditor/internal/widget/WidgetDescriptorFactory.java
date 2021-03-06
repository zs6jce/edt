/*******************************************************************************
 * Copyright © 2008, 2013 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *
 *******************************************************************************/
package org.eclipse.edt.ide.rui.visualeditor.internal.widget;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.edt.compiler.internal.eglar.EglarFile;
import org.eclipse.edt.compiler.internal.eglar.EglarFileCache;
import org.eclipse.edt.ide.core.internal.lookup.ProjectEnvironment;
import org.eclipse.edt.ide.core.internal.lookup.ProjectEnvironmentManager;
import org.eclipse.edt.ide.rui.document.utils.IVEConstants;
import org.eclipse.edt.ide.rui.visualeditor.internal.editor.EvConstants;
import org.eclipse.edt.ide.rui.visualeditor.plugin.Activator;
import org.eclipse.edt.mof.egl.Annotation;
import org.eclipse.edt.mof.egl.ArrayType;
import org.eclipse.edt.mof.egl.EnumerationEntry;
import org.eclipse.edt.mof.egl.ExternalType;
import org.eclipse.edt.mof.egl.Field;
import org.eclipse.edt.mof.egl.LogicAndDataPart;
import org.eclipse.edt.mof.egl.PartNotFoundException;
import org.eclipse.edt.mof.egl.StructPart;
import org.eclipse.edt.mof.egl.Type;
import org.eclipse.edt.mof.egl.utils.TypeUtils;
import org.eclipse.edt.mof.impl.Bootstrap;
import org.eclipse.edt.mof.serialization.Environment;
import org.eclipse.edt.mof.utils.EList;
import org.eclipse.edt.mof.utils.NameUtile;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.ImageData;

public class WidgetDescriptorFactory {
	private static final String defaultTemplate = "${typeName}{}";
	
	private static final String		VEWIDGET					= NameUtile.getAsName( "eglx.ui.rui.VEWidget" );
	private static final String		VEDATATEMPLATE				= NameUtile.getAsName( "eglx.ui.rui.VEDataTemplate" );
	private static final String		VEPROPERTY					= NameUtile.getAsName( "eglx.ui.rui.VEProperty" );
	private static final String		VEEVENT						= NameUtile.getAsName( "eglx.ui.rui.VEEvent" );

	private static final String		WIDGET_CATEGORY				= NameUtile.getAsName( "category" );
	private static final String		WIDGET_DISPLAYNAME			= NameUtile.getAsName( "displayName" );
	private static final String		WIDGET_SMALLICON			= NameUtile.getAsName( "smallIcon" );
	private static final String		WIDGET_LARGEICON			= NameUtile.getAsName( "largeIcon" );
	private static final String		WIDGET_TEMPLATE				= NameUtile.getAsName( "template" );
	private static final String		WIDGET_DATA_TEMPLATES		= NameUtile.getAsName( "dataTemplates" );
	private static final String		WIDGET_DESCRIPTION			= NameUtile.getAsName( "description" );
	private static final String		WIDGET_CONTAINER			= NameUtile.getAsName( "container" );
	private static final String		WIDGET_LAYOUTDATATYPE		= NameUtile.getAsName( "layoutDataType" );
	private static final String		WIDGET_PROVIDER				= NameUtile.getAsName( "provider" );
	
	private static final String		DATA_TEMPLATE_NAME			= NameUtile.getAsName( "name" );
	private static final String		DATA_TEMPLATE_DATA_MAPPING	= NameUtile.getAsName( "dataMapping" );
	private static final String		DATA_TEMPLATE_PURPOSES	    = NameUtile.getAsName( "purposes" );
	private static final String		DATA_TEMPLATE_TEMPLATE   	= NameUtile.getAsName( "template" );
	private static final String		DATA_TEMPLATE_GEN_CONTROLLER = NameUtile.getAsName( "genController" );
	private static final String		DATA_TEMPLATE_FORM_MANAGER_TEMPLATE	= NameUtile.getAsName( "formManagerTemplate" );
	private static final String		DATA_TEMPLATE_CHILD_LAYOUT_DATA_TEMPLATE	= NameUtile.getAsName( "childLayoutDataTemplate" );
	private static final String		DATA_TEMPLATE_CHILD_NAME_LABEL_TEMPLATE	= NameUtile.getAsName( "childNameLabelTemplate" );
	private static final String		DATA_TEMPLATE_CHILD_ERROR_LABEL_TEMPLATE	= NameUtile.getAsName( "childErrorLabelTemplate" );
	private static final String		DATA_TEMPLATE_CHILD_CONTROLLER_TEMPLATE	= NameUtile.getAsName( "childControllerTemplate" );
	private static final String		DATA_TEMPLATE_CHILD_CONTROLLER_VALID_STATE_SETTER_TEMPLATE	= NameUtile.getAsName( "childControllerValidStateSetterTemplate" );
	private static final String		DATA_TEMPLATE_CHILD_CONTROLLER_PUBLISH_MESSAGE_HELPER_TEMPLATE	= NameUtile.getAsName( "childControllerPublishMessageHelperTemplate" );
	private static final String		DATA_TEMPLATE_CHILD_FORM_FIELD_TEMPLATE	= NameUtile.getAsName( "childFormFieldTemplate" );
	
	private static final String		DATA_MAPPING_FOR_ARRAY   	= NameUtile.getAsName( "forArray" );
	private static final String  	DATA_MAPPING_GEN_CHILD_WIDGET = NameUtile.getAsName( "genChildWidget" );
	private static final String  	DATA_MAPPING_IS_CONTAINER 	= NameUtile.getAsName( "isContainer" );
	private static final String  	DATA_MAPPING_IS_DEFAULT 	= NameUtile.getAsName( "isDefault" );
	private static final String		DATA_MAPPING_MAPPINGS    	= NameUtile.getAsName( "mappings" );
	
	private static final String		PROPERTY_PROPERTYTYPE		= NameUtile.getAsName( "propertyType" );
	private static final String		PROPERTY_DISPLAYNAME		= NameUtile.getAsName( "displayName" );
	private static final String		PROPERTY_DEFAULT			= NameUtile.getAsName( "default" );
	private static final String		PROPERTY_CATEGORY			= NameUtile.getAsName( "category" );
	private static final String		PROPERTY_CHOICES			= NameUtile.getAsName( "choices" );

	private static final String		PROPERTYCHOICE_DISPLAYNAME	= NameUtile.getAsName( "displayName" );
	private static final String		PROPERTYCHOICE_ID			= NameUtile.getAsName( "id" );

	private static final String		EVENT_DISPLAYNAME			= NameUtile.getAsName( "displayName" );
	
	private static final String		PROPERTY_FILTER_TYPE 		= NameUtile.getAsName( "propertyFilterType" );
	private static final String		EVENT_FILTER_TYPE			= NameUtile.getAsName( "eventFilterType" );
	private static final String		PROPERTY_FILTER 			= NameUtile.getAsName( "propertyFilter" );
	private static final String		EVENT_FILTER				= NameUtile.getAsName( "eventFilter" );

	private static final boolean	DEBUG						= false;

	private IProject				project;
	private ProjectEnvironment		environment;
	private EglarFile               eglarFile;
	
	public WidgetDescriptorFactory( IProject project ) {
		this.project = project;
		this.environment = ProjectEnvironmentManager.getInstance().getProjectEnvironment( project );
		Bootstrap.initialize(this.environment.getIREnvironment());
		Environment.pushEnv(this.environment.getIREnvironment());
	}

	/**
	 * 
	 * @param environment
	 * @param packageName
	 * @param partName
	 * @return null if any exceptions are thrown while creating the descriptor or if the part could not be found for some reason
	 */
	public WidgetDescriptor createWidgetDescriptor( String packageName, String partName ) {

		WidgetDescriptor widgetDescriptor = null;

		try {
			LogicAndDataPart part = (LogicAndDataPart)environment.findPart( packageName, partName );
			
			if( part != null && (part.getAnnotation( VEWIDGET ) != null || part.getAnnotation( VEDATATEMPLATE ) != null) ) {
				if( DEBUG ) {
					System.out.println( "\nStarting to create Descriptor for Part: " + partName );
				}
				widgetDescriptor = new WidgetDescriptor();
				widgetDescriptor._strProjectName = this.project.getName();
				processWidget( part, widgetDescriptor );
				
				widgetDescriptor.setPackage( packageName );			
				processWidgetFields( part, widgetDescriptor );
				
				if( DEBUG ) {
					System.out.println( "Finished creating Descriptor for Part: " + partName + "\n" );
				}
			}
		}
		catch( PartNotFoundException pnfe ) {
			//do nothing. this must be the case that new created RUIWidget which is not compiled.
		}
		catch( Exception e ) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.PLUGIN_ID, "Error processing VEWidget annotation for part partName " + partName, e));
			widgetDescriptor = null;
		}

		return widgetDescriptor;
	}
	
	public void setEglarFile( File _eglarFile ) {
		if ( _eglarFile == null ) {
			eglarFile = null;
			return;
		}
		if ( !_eglarFile.exists() ) {
			IResource resource = ResourcesPlugin.getWorkspace().getRoot().findMember( "/" + _eglarFile.getPath() );
			if ( resource.exists() ) {
				_eglarFile = new File( resource.getLocation().toOSString() );
			}
		}
		if ( eglarFile == null || !_eglarFile.getPath().equals( eglarFile.getName() ) ) {
			try {
				eglarFile = EglarFileCache.instance.getEglarFile( _eglarFile );
			} catch (IOException e) {
				eglarFile = null;
			}
		}
	}

	private void processWidget( LogicAndDataPart part, WidgetDescriptor widgetDescriptor ) {
		if( DEBUG ) {
			System.out.println( "Start processing VEWidget annotation" );
		}

		Annotation widgetAnnotation = part.getAnnotation( VEWIDGET );

		// ID
		processWidgetID( part, widgetDescriptor );

		if(widgetAnnotation != null){
			// Group
			processWidgetGroup( widgetAnnotation, widgetDescriptor );

			// DisplayName
			processWidgetDisplayName( widgetAnnotation, widgetDescriptor );

			// Small Icon
			processWidgetSmallIcon( widgetAnnotation, widgetDescriptor );

			// Large Icon
			processWidgetLargeIcon( widgetAnnotation, widgetDescriptor );

			// Template
			processWidgetTemplate( widgetAnnotation, widgetDescriptor );
			
			// Description
			processWidgetDescription( widgetAnnotation, widgetDescriptor );
			
			// Excluded Properties and Events
			processWidgetExcludedFields( widgetAnnotation, widgetDescriptor );
			
			// Container
			processWidgetContainer( widgetAnnotation, widgetDescriptor );

			// Provider
			processWidgetProvider( widgetAnnotation, widgetDescriptor );
			
			if( DEBUG ) {
				System.out.println( "Finished processing VEWidget annotation" );
			}
		}
		
		Annotation dataTemplateAnnotation = part.getAnnotation( VEDATATEMPLATE );
		
		if(dataTemplateAnnotation != null){		
			// DataTemplates
			processWidgetDataTemplates( dataTemplateAnnotation, widgetDescriptor);
			
			if( DEBUG ) {
				System.out.println( "Finished processing VEDataTemplate annotation" );
			}
		}
	}

	private void processWidgetID( LogicAndDataPart part, WidgetDescriptor widgetDescriptor ) {
		widgetDescriptor._strType = part.getCaseSensitiveName();

		if( DEBUG ) {
			System.out.println( "Widget ID: " + widgetDescriptor.getID() );
		}
	}

	private void processWidgetGroup( Annotation widgetAnnotation, WidgetDescriptor widgetDescriptor ) {
		String group = (String)widgetAnnotation.getValue( WIDGET_CATEGORY );

		if(!isEmpty(group)) {
			widgetDescriptor._strGroup = group;
		}

		if( DEBUG ) {
			System.out.println( "Widget Group: " + widgetDescriptor.getGroup() );
		}
	}

	private void processWidgetDescription( Annotation widgetAnnotation, WidgetDescriptor widgetDescriptor ) {
		String description = (String)widgetAnnotation.getValue( WIDGET_DESCRIPTION );
		
		if(!isEmpty(description)){
			widgetDescriptor._strDescription = description;
		}
		
		if( DEBUG ) {
			System.out.println( "Widget Description: " + widgetDescriptor.getDescription() );
		}
	}
	
	private void processWidgetProvider( Annotation widgetAnnotation, WidgetDescriptor widgetDescriptor ) {
		String provider = (String)widgetAnnotation.getValue( WIDGET_PROVIDER );

		if(!isEmpty(provider)){
			widgetDescriptor._strProvider = provider;
		}
		
		if( DEBUG ) {
			System.out.println( "Widget Provider: " + widgetDescriptor.getProvider() );
		}
	}
	
	private void processWidgetTemplate( Annotation widgetAnnotation, WidgetDescriptor widgetDescriptor ) {
		String template = (String)widgetAnnotation.getValue( WIDGET_TEMPLATE);
		
		if(!isEmpty(template)){
			widgetDescriptor._strTemplate = template;
		}else{
			widgetDescriptor._strTemplate = defaultTemplate;
		}
		
		if( DEBUG ) {
			System.out.println( "Widget Template: " + widgetDescriptor.getTemplate() );
		}
	}

	private void processWidgetDataTemplates( Annotation widgetAnnotation, WidgetDescriptor widgetDescriptor ){
		EList dataTemplateAnnotations = (EList)widgetAnnotation.getValue(WIDGET_DATA_TEMPLATES);
		if(dataTemplateAnnotations != null){
			widgetDescriptor._DataTemplates = new ArrayList<DataTemplate>();
			for(int i=0; i<dataTemplateAnnotations.size(); i++){
				Annotation dataTemplateAnnotation = (Annotation)dataTemplateAnnotations.get(i);
				DataTemplate dataTemplate = new DataTemplate(widgetDescriptor);
				widgetDescriptor._DataTemplates.add(dataTemplate);
				// name
				String name = (String)dataTemplateAnnotation.getValue(DATA_TEMPLATE_NAME);
				if(!isEmpty(name)){
					dataTemplate.setName(name);
				}
				
				// purposes
				EList oPurposes = (EList)dataTemplateAnnotation.getValue(DATA_TEMPLATE_PURPOSES);
				EnumerationEntry[] purposes = new EnumerationEntry[oPurposes.size()];
				for(int j=0; j<oPurposes.size(); j++){
					purposes[j] = (EnumerationEntry)oPurposes.get(j);
				}
				dataTemplate.setPurposes(purposes);
				
				// genController
				Boolean genController = (Boolean)dataTemplateAnnotation.getValue(DATA_TEMPLATE_GEN_CONTROLLER);
				dataTemplate.setGenController(genController);
				
				// template
				String template = (String)dataTemplateAnnotation.getValue(DATA_TEMPLATE_TEMPLATE);
				if(!isEmpty(template)){
					dataTemplate.setTemplate(template);
				}
				
				// form manager template
				String formManagerTemplate = (String)dataTemplateAnnotation.getValue(DATA_TEMPLATE_FORM_MANAGER_TEMPLATE);
				if(!isEmpty(formManagerTemplate)){
					dataTemplate.setFormManagerTemplate(formManagerTemplate);
				}
				
				// layout data template
				String childLayoutDataTemplate = (String)dataTemplateAnnotation.getValue(DATA_TEMPLATE_CHILD_LAYOUT_DATA_TEMPLATE);
				if(!isEmpty(childLayoutDataTemplate)){
					dataTemplate.setChildLayoutDataTemplate(childLayoutDataTemplate);
				}
				
				// name label template
				String childNameLabelTemplate = (String)dataTemplateAnnotation.getValue(DATA_TEMPLATE_CHILD_NAME_LABEL_TEMPLATE);
				if(!isEmpty(childNameLabelTemplate)){
					dataTemplate.setChildNameLabelTemplate(childNameLabelTemplate);
				}
				
				// error label template
				String childErrorLabelTemplate = (String)dataTemplateAnnotation.getValue(DATA_TEMPLATE_CHILD_ERROR_LABEL_TEMPLATE);
				if(!isEmpty(childErrorLabelTemplate)){
					dataTemplate.setChildErrorLabelTemplate(childErrorLabelTemplate);
				}
				
				// controller template
				String childControllerTemplate = (String)dataTemplateAnnotation.getValue(DATA_TEMPLATE_CHILD_CONTROLLER_TEMPLATE);
				if(!isEmpty(childControllerTemplate)){
					dataTemplate.setChildControllerTemplate(childControllerTemplate);
				}

				// controller valid state setter template
				String childControllerValidStateSetterTemplate = (String)dataTemplateAnnotation.getValue(DATA_TEMPLATE_CHILD_CONTROLLER_VALID_STATE_SETTER_TEMPLATE);
				if(!isEmpty(childControllerValidStateSetterTemplate)){
					dataTemplate.setChildControllerValidStateSetterTemplate(childControllerValidStateSetterTemplate);
				}
				
				// controller publish message helper template
				String childControllerPublishMessageHelperTemplate = (String)dataTemplateAnnotation.getValue(DATA_TEMPLATE_CHILD_CONTROLLER_PUBLISH_MESSAGE_HELPER_TEMPLATE);
				if(!isEmpty(childControllerPublishMessageHelperTemplate)){
					dataTemplate.setChildControllerPublishMessageHelperTemplate(childControllerPublishMessageHelperTemplate);
				}
				
				// form field template
				String childFormFieldTemplate = (String)dataTemplateAnnotation.getValue(DATA_TEMPLATE_CHILD_FORM_FIELD_TEMPLATE);
				if(!isEmpty(childFormFieldTemplate)){
					dataTemplate.setChildFormFieldTemplate(childFormFieldTemplate);
				}				
				
				// data mapping
				Annotation dataMappingAnnotation = (Annotation)dataTemplateAnnotation.getValue(DATA_TEMPLATE_DATA_MAPPING);
				if(dataMappingAnnotation != null){
					// forArray
					Boolean forArray = (Boolean)dataMappingAnnotation.getValue(DATA_MAPPING_FOR_ARRAY);
					dataTemplate.getDataMapping().setForArray(forArray);
					
					// isContainer
					Boolean isContainer = (Boolean)dataMappingAnnotation.getValue(DATA_MAPPING_IS_CONTAINER);
					dataTemplate.getDataMapping().setContainer(isContainer);
					
					// isDefault
					Boolean isDefault = (Boolean)dataMappingAnnotation.getValue(DATA_MAPPING_IS_DEFAULT);
					dataTemplate.getDataMapping().setDefault(isDefault);
					
					// genChildWidget
					Boolean genChildWidget = (Boolean)dataMappingAnnotation.getValue(DATA_MAPPING_GEN_CHILD_WIDGET);
					dataTemplate.getDataMapping().setGenChildWidget(genChildWidget);
					
					// mappings
					EList oMappings = (EList)dataMappingAnnotation.getValue(DATA_MAPPING_MAPPINGS);
					EnumerationEntry[] mappings = new EnumerationEntry[oMappings.size()];
					for(int j=0; j<oMappings.size(); j++){
						mappings[j] = (EnumerationEntry)oMappings.get(j);
					}
					dataTemplate.getDataMapping().setMappings(mappings);
				}
			}
		}
		if( DEBUG ) {
			System.out.println( "Widget DataTemplates: " + widgetDescriptor._DataTemplates.toArray().toString() );
		}
	}

	private void processWidgetDisplayName( Annotation widgetAnnotation, WidgetDescriptor widgetDescriptor ) {
		String widgetValue = (String)widgetAnnotation.getValue( WIDGET_DISPLAYNAME );

		if( !isEmpty( widgetValue ) ) {
			widgetDescriptor._strLabel = widgetValue;
		}
		else {
			widgetDescriptor._strLabel = widgetDescriptor._strType;
		}

		if( DEBUG ) {
			System.out.println( "Widget Display Name: " + widgetDescriptor.getLabel() );
		}
	}

	private void processWidgetSmallIcon( Annotation widgetAnnotation, WidgetDescriptor widgetDescriptor ) {
		String widgetValue = (String)widgetAnnotation.getValue( WIDGET_SMALLICON );

		if( !isEmpty( widgetValue ) ) {
			try {
				IResource member = project.findMember( widgetValue );
				if(member != null && member.getType() == IResource.FILE){
						URL url = member.getLocationURI().toURL();
						ImageDescriptor iconSmall = ImageDescriptor.createFromURL( url );
						widgetDescriptor._iconSmall = iconSmall;
				} else if ( eglarFile != null ) {
					ZipEntry iconEntry = eglarFile.getEntry( widgetValue );
					if ( iconEntry != null ) {
						ImageDescriptor iconSmall = ImageDescriptor.createFromImageData( new ImageData( eglarFile.getInputStream( iconEntry ) ) );
						widgetDescriptor._iconSmall = iconSmall;
					}
				}
			}catch( Exception e ) {
				Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.PLUGIN_ID, "Error processing small icon", e));
			}
		}else{
			widgetDescriptor._iconSmall = Activator.getImageDescriptor(EvConstants.ICON_DEFAULT_WIDGET_SMALL);
		}

		if( DEBUG ) {
			System.out.println( "Widget Icon Small: " + widgetValue );
		}
	}

	private void processWidgetLargeIcon( Annotation widgetAnnotation, WidgetDescriptor widgetDescriptor ) {
		String widgetValue = (String)widgetAnnotation.getValue( WIDGET_LARGEICON );

		if( !isEmpty( widgetValue ) ) {
			try{
				IResource member = project.findMember( widgetValue );
				if(member != null && member.getType() == IResource.FILE){
						URL url = member.getLocationURI().toURL();
						ImageDescriptor iconLarge = ImageDescriptor.createFromURL( url );
						widgetDescriptor._iconLarge = iconLarge;
				} else if ( eglarFile != null ) {
					ZipEntry iconEntry = eglarFile.getEntry( widgetValue );
					if ( iconEntry != null ) {
						ImageDescriptor iconLarge = ImageDescriptor.createFromImageData( new ImageData( eglarFile.getInputStream( iconEntry ) ) );
						widgetDescriptor._iconLarge = iconLarge;
					}
				}
			}catch( Exception e ) {
				Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.PLUGIN_ID, "Error processing large icon", e));
			}
		}else{
			widgetDescriptor._iconLarge = Activator.getImageDescriptor(EvConstants.ICON_DEFAULT_WIDGET_LARGE);
		}

		if( DEBUG ) {
			System.out.println( "Widget Icon Large: " + widgetValue );
		}
	}
	
	private void processWidgetExcludedFields( Annotation widgetAnnotation, WidgetDescriptor widgetDescriptor ) {
		EnumerationEntry propertyFilterType = (EnumerationEntry)widgetAnnotation.getValue( PROPERTY_FILTER_TYPE );
		EList excludedProperties = (EList)widgetAnnotation.getValue( PROPERTY_FILTER );
		if ( propertyFilterType != null ) {
			widgetDescriptor._propertyFilterType = propertyFilterType.getValue();
		}
		if( excludedProperties != null && excludedProperties.size() > 0 ) {
			for ( int i = 0; i < excludedProperties.size(); i ++ ) {
				widgetDescriptor._propertyFilter.add( (String)excludedProperties.get(i) );
			}
		}
		
		EnumerationEntry eventFilterType = (EnumerationEntry)widgetAnnotation.getValue( EVENT_FILTER_TYPE );
		EList excludedEvents = (EList)widgetAnnotation.getValue( EVENT_FILTER );
		if ( eventFilterType != null ) {
			widgetDescriptor._eventFilterType = eventFilterType.getValue();
		}
		if( excludedEvents != null && excludedEvents.size() > 0 ) {
			for ( int i = 0; i < excludedEvents.size(); i ++ ) {
				widgetDescriptor._eventFilter.add( (String)excludedEvents.get(i));
			}
		}
	}

	private void processWidgetContainer(Annotation widgetAnnotation, WidgetDescriptor widgetDescriptor) {
		boolean isContainer = false;
		WidgetLayoutDescriptor layoutDescriptor = null;
		
		Annotation containerAnnotation = (Annotation) widgetAnnotation.getValue( WIDGET_CONTAINER );
		if(containerAnnotation != null){
			isContainer = true;
			String layoutDataTypeTemplate = (String) containerAnnotation.getValue( WIDGET_LAYOUTDATATYPE );
			if (!isEmpty(layoutDataTypeTemplate)) {
				try {
					layoutDescriptor = new WidgetLayoutDescriptor();
					widgetDescriptor.setChildLayoutDataTemplate( layoutDataTypeTemplate );

					String layoutDataType = layoutDataTypeTemplate.replaceFirst( ".*\\$\\{typeName:\\s*", "" );
					layoutDataType = layoutDataType.replaceFirst( "\\s*}.*", "" );
					int lastIndex = layoutDataType.lastIndexOf( '.' );
					String typeName = null;
					String packageName;
					if ( lastIndex < 0 ) {
						typeName = layoutDataType;
						packageName = "";
					} else {
						typeName = layoutDataType.substring( lastIndex + 1 );
						layoutDataType = layoutDataType.substring( 0, lastIndex );
						packageName = layoutDataType;
					}
					LogicAndDataPart part = (LogicAndDataPart)environment.findPart( NameUtile.getAsName( packageName ) , NameUtile.getAsName( typeName ) );
					List<Field> allFields = getAllFields(part);
					for( int i = 0; i < allFields.size(); i++ ) {
						Field field = allFields.get(i);
						Annotation propertyAnnotation = field.getAnnotation( VEPROPERTY );
						if( propertyAnnotation != null ) {
							processProperty( propertyAnnotation, field, layoutDescriptor );
						}
					}
				} catch ( Exception e ) {
					Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.PLUGIN_ID, "Error processing Container annotation for Widget", e));
					e.printStackTrace();
				}
			}
		}
		widgetDescriptor._bContainer = isContainer;
		widgetDescriptor._layoutDescriptor = layoutDescriptor;
		
		if( DEBUG ) {
			System.out.println( "Container: " + widgetDescriptor.isContainer() );
		}		
	}

	private void processWidgetFields( LogicAndDataPart part, WidgetDescriptor widgetDescriptor ) {
		List<Field> allFields = getAllFields(part);
		for( int i = 0; i < allFields.size(); i++ ) {
			Field field = allFields.get(i);
			Annotation propertyAnnotation = field.getAnnotation( VEPROPERTY );
			if( propertyAnnotation != null ) {
				processProperty( propertyAnnotation, field, widgetDescriptor );
			}
			else {
				Annotation eventAnnotation = field.getAnnotation( VEEVENT );
				if( eventAnnotation != null ) {
					processEvent( eventAnnotation, field, widgetDescriptor );
				}
			}
		}
	}
	
	private List<Field> getAllFields( LogicAndDataPart part ){
		List<Field> fields = new ArrayList<Field>();
		List<ExternalType> ancestors = getAllAncestors(part);
		for(ExternalType ancestor : ancestors){
			fields.addAll( ancestor.getFields() );
		}
		fields.addAll( part.getFields() );
		return fields;
	}
	
	private List<ExternalType> getAllAncestors( LogicAndDataPart part ){
		List<ExternalType> ancestors = new ArrayList<ExternalType>();
		List<StructPart> parents = part.getSuperTypes();
		for(StructPart parent : parents){
			if(parent instanceof ExternalType){
				ExternalType externalTypeParent = (ExternalType)parent;
				ancestors.add(externalTypeParent);
				getParents(ancestors, externalTypeParent);
			}
		}
		return ancestors;
	}
	
	private void getParents(List<ExternalType> ancestors, ExternalType part){
		List<StructPart> parents = part.getSuperTypes();
		for(StructPart parent : parents){
			if(parent instanceof ExternalType){
				ExternalType externalTypeParent = (ExternalType)parent;
				ancestors.add(externalTypeParent);
				getParents(ancestors, externalTypeParent);
			}
		}
	}

	private void processEvent( Annotation annotation, Field field, WidgetDescriptor widgetDescriptor ) {
		if( DEBUG ) {
			System.out.println( "Start processing VEEvent annotation for Field: " + field.getCaseSensitiveName() );
		}

		WidgetEventDescriptor eventDescriptor = new WidgetEventDescriptor();

		//Id
		processEventID( field, eventDescriptor );

		//Display Name
		processEventDisplayName( annotation, eventDescriptor );
		
		//Excluded Event
		processEventExcluded( field, eventDescriptor, widgetDescriptor );

		widgetDescriptor.addEventDescriptor( eventDescriptor );

		if( DEBUG ) {
			System.out.println( "Finished processing VEEvent annotation for Field: " + field.getCaseSensitiveName() );
		}
	}

	private void processEventID( Field field, WidgetEventDescriptor eventDescriptor ) {
		eventDescriptor._strID = field.getCaseSensitiveName();

		if( DEBUG ) {
			System.out.println( "Event ID: " + eventDescriptor.getID() );
		}
	}

	private void processEventDisplayName( Annotation propertyAnnotation, WidgetEventDescriptor eventDescriptor ) {
		String eventValue = (String)propertyAnnotation.getValue( EVENT_DISPLAYNAME );

		if( !isEmpty(eventValue ) ) {
			eventDescriptor._strLabel = eventValue;
		}
		else {
			eventDescriptor._strLabel = eventDescriptor._strID;
		}

		if( DEBUG ) {
			System.out.println( "Event Display Name: " + eventDescriptor.getLabel() );
		}
	}
	
	private void processEventExcluded( Field field, WidgetEventDescriptor eventDescriptor, WidgetDescriptor widgetDescriptor ) {
		if ( !isPredefined( field, widgetDescriptor ) ) {
			// The event is not inherited from parent Widget. That means this Event is defined by this Widget itself.
			return;
		}
		switch ( widgetDescriptor._eventFilterType ) {
		case WidgetDescriptor.INCLUDE_ALL :
			eventDescriptor._excluded = false;
			break;

		case WidgetDescriptor.EXCLUDE_ALL :
			eventDescriptor._excluded = true;
			break;

		case WidgetDescriptor.EXCLUDE_ALL_EXCEPT :
		case WidgetDescriptor.INCLUDE_ALL_EXCEPT :
			boolean includedInFilter = false;
			for ( int i = 0; i < widgetDescriptor._eventFilter.size(); i ++ ) {
				String excludedEvent = (String)widgetDescriptor._eventFilter.get( i );
				if ( eventDescriptor.getID().equalsIgnoreCase( excludedEvent ) ) {
					includedInFilter = true;
					break;
				}
			}
			eventDescriptor._excluded = ( widgetDescriptor._eventFilterType == WidgetDescriptor.EXCLUDE_ALL_EXCEPT ) 
											? !includedInFilter	: includedInFilter;
			break;
	}
	
	if( DEBUG ) {
		System.out.println( "Event Excluded: " + eventDescriptor._strID + ":" + eventDescriptor._excluded );
	}
	}

	private void processProperty( Annotation annotation, Field field,  VEPropertyContainer widgetDescriptor ) {

		if( DEBUG ) {
			System.out.println( "Start processing VEProperty annotation for Field: " + field.getCaseSensitiveName() );
		}

		WidgetPropertyDescriptor propertyDescriptor = new WidgetPropertyDescriptor();

		//Choices
		processPropertyChoices( annotation, propertyDescriptor );

		//Category
		processPropertyCategory( annotation, propertyDescriptor );

		//Default				
		processPropertyDefault( annotation, propertyDescriptor );

		//ID
		processPropertyID( field, propertyDescriptor );

		//DisplayName
		processPropertyDisplayName( annotation, field, propertyDescriptor );

		//Type
		processPropertyType( annotation, field, propertyDescriptor );

		if ( widgetDescriptor instanceof WidgetDescriptor) {
			//Excluded Property
			processPropertyExcluded( field, propertyDescriptor, (WidgetDescriptor)widgetDescriptor );
		}		
		widgetDescriptor.addPropertyDescriptor( propertyDescriptor );

		if( DEBUG ) {
			System.out.println( "Finished processing VEProperty annotation for Field: " + field.getCaseSensitiveName() );
		}
	}

	private void processPropertyExcluded( Field field, WidgetPropertyDescriptor propertyDescriptor, WidgetDescriptor widgetDescriptor ) {
		if ( !isPredefined( field, widgetDescriptor ) ) {
			// The Property is not inherited from parent Widget. That means this Property is defined by this Widget itself.
			return;
		}
		switch ( widgetDescriptor._propertyFilterType ) {
			case WidgetDescriptor.INCLUDE_ALL :
				propertyDescriptor._excluded = false;
				break;

			case WidgetDescriptor.EXCLUDE_ALL :
				propertyDescriptor._excluded = true;
				break;

			case WidgetDescriptor.EXCLUDE_ALL_EXCEPT :
			case WidgetDescriptor.INCLUDE_ALL_EXCEPT :
				boolean includedInFilter = false;
				for ( int i = 0; i < widgetDescriptor._propertyFilter.size(); i ++ ) {
					String excludedProperty = (String)widgetDescriptor._propertyFilter.get( i );
					if ( propertyDescriptor.getID().equalsIgnoreCase( excludedProperty ) ) {
						includedInFilter = true;
						break;
					}
				}
				propertyDescriptor._excluded = ( widgetDescriptor._propertyFilterType == WidgetDescriptor.EXCLUDE_ALL_EXCEPT ) 
												? !includedInFilter	: includedInFilter;
				break;
		}
		
		if( DEBUG ) {
			System.out.println( "Property Excluded: " + propertyDescriptor._strID + ":" + propertyDescriptor._excluded );
		}
	}
	
	private void processPropertyChoices( Annotation propertyAnnotation, WidgetPropertyDescriptor propertyDescriptor ) {		
		EList propertyValue = (EList)propertyAnnotation.getValue( PROPERTY_CHOICES );

		if( propertyValue != null ) {
			for( int i = 0; i < propertyValue.size(); i++ ) {
				Annotation choice = (Annotation)propertyValue.get(i);

				//id & displayName
				WidgetPropertyChoice propertyChoice = new WidgetPropertyChoice( (String)choice.getValue( PROPERTYCHOICE_ID ), (String)choice.getValue( PROPERTYCHOICE_DISPLAYNAME ) );
				propertyDescriptor.addChoice( propertyChoice );

				if( DEBUG ) {
					System.out.println( "Finished processing VEPropertyChoice.  ID: " + propertyChoice.getID() + ", DisplayName: " + propertyChoice.getLabel() );
				}
			}
		}
	}

	private void processPropertyCategory( Annotation propertyAnnotation, WidgetPropertyDescriptor propertyDescriptor ) {
		propertyDescriptor._strCategory = (String)propertyAnnotation.getValue( PROPERTY_CATEGORY );

		if(propertyDescriptor._strCategory.equals("")){
			propertyDescriptor._strCategory = null;
		}
		
		if( DEBUG ) {
			System.out.println( "Property Category: " + propertyDescriptor.getCategory() );
		}
	}

	private void processPropertyID( Field field, WidgetPropertyDescriptor propertyDescriptor ) {
		propertyDescriptor._strID = field.getCaseSensitiveName();

		if( DEBUG ) {
			System.out.println( "Property ID: " + propertyDescriptor.getID() );
		}
	}

	private void processPropertyDefault( Annotation propertyAnnotation, WidgetPropertyDescriptor propertyDescriptor ) {
		propertyDescriptor._strDefault = (String)propertyAnnotation.getValue( PROPERTY_DEFAULT );

		if(propertyDescriptor._strDefault.equals("")){
			propertyDescriptor._strDefault = null;
		}
		
		if( DEBUG ) {
			System.out.println( "Field Default: " + propertyDescriptor.getDefault() );
		}
	}

	private void processPropertyDisplayName( Annotation propertyAnnotation, Field field, WidgetPropertyDescriptor propertyDescriptor ) {
		String propertyValue = (String)propertyAnnotation.getValue( PROPERTY_DISPLAYNAME );

		if(!isEmpty( propertyValue)) {
			propertyDescriptor._strLabel = propertyValue;
		}
		else {
			propertyDescriptor._strLabel = propertyDescriptor._strID;
		}
		if( DEBUG ) {
			System.out.println( "Field Display Name: " + propertyDescriptor.getID() );
		}
	}

	private void processPropertyType( Annotation propertyAnnotation, Field field, WidgetPropertyDescriptor propertyDescriptor ) {		
		String propertyValue = (String)propertyAnnotation.getValue( PROPERTY_PROPERTYTYPE );


		if( !isEmpty(propertyValue) ) {
			propertyDescriptor._strType = propertyValue;
		}
		else {
			propertyDescriptor._strType = convertEGLTypeToPropertyType( field.getType() );
		}
		if( DEBUG ) {
			System.out.println( "Field Type: " + propertyDescriptor.getType() );
		}
	}
	
	private String convertEGLTypeToPropertyType( Type type ) {
		if(TypeUtils.Type_INT.equals(type)){
			return IVEConstants.INTEGER_TYPE;
		}else if(TypeUtils.Type_STRING.equals(type)){
			return IVEConstants.STRING_TYPE;
		}else if(TypeUtils.Type_BOOLEAN.equals(type)){
			return IVEConstants.BOOLEAN_TYPE;
		}else if(TypeUtils.getTypeKind(type) == TypeUtils.TypeKind_ARRAY){
			Type arrayElementType = ((ArrayType)type).getElementType();
			//TODO - Add support for arrays of type other than string?
			if(TypeUtils.Type_INT.equals(arrayElementType)){
				//return IVEConstants.INTEGER_TYPE;
				return IVEConstants.STRING_ARRAY_TYPE;
			}else if(TypeUtils.Type_STRING.equals(arrayElementType)){
				return IVEConstants.STRING_ARRAY_TYPE;
			}else if(TypeUtils.Type_BOOLEAN.equals(arrayElementType)){
				//return IVEConstants.BOOLEAN_TYPE;
				return IVEConstants.STRING_ARRAY_TYPE;
			}else{
				//TODO - Return String if it's an unknown type?
				return IVEConstants.STRING_ARRAY_TYPE;
			}
		}
		else {
			//TODO - Return String if it's an unknown type?
			return IVEConstants.STRING_TYPE;
		}
	}
	
	private boolean isPredefined( Field field, WidgetDescriptor widgetDescriptor ) {
		if ( field.isImplicit() ) {
			//this means that this field is defined in RUIWidget
			return true;
		}
		LogicAndDataPart part = (LogicAndDataPart)field.getContainer();
		return !(widgetDescriptor.getPackage() == null ? widgetDescriptor.getType() : widgetDescriptor.getPackage() + "." + widgetDescriptor.getType() ).equalsIgnoreCase( part.getTypeSignature()) ;
	}
	
	private boolean isEmpty( String s ) {
		return (s == null || s.length() == 0);
	}
}
