/*******************************************************************************
 * Copyright © 2012, 2013 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *
 *******************************************************************************/
	if (egl.eze$$userLibs) egl.eze$$userLibs.push('org.eclipse.edt.eunit.runtime.ConstantsLib');
	else egl.eze$$userLibs = ['org.eclipse.edt.eunit.runtime.ConstantsLib'];
	egl.defineRUILibrary('org.eclipse.edt.eunit.runtime', 'ConstantsLib',
	{
		'eze$$fileName': 'org/eclipse/edt/eunit/runtime/ConstantsLib.egl',
		'eze$$runtimePropertiesFile': 'org.eclipse.edt.eunit.runtime.ConstantsLib',
			"constructor": function() {
				if(egl.org.eclipse.edt.eunit.runtime.ConstantsLib['$inst']) return egl.org.eclipse.edt.eunit.runtime.ConstantsLib['$inst'];
				egl.org.eclipse.edt.eunit.runtime.ConstantsLib['$inst']=this;
			}
			,
			"eze$$setEmpty": function() {
				this.NEWLINE = "";
				egl.atLine(this.eze$$fileName,18,681,6, this);
				this.NEWLINE = "\r\n";
				this.EXIT_PREFIX = "";
				egl.atLine(this.eze$$fileName,19,718,10, this);
				this.EXIT_PREFIX = "STATUS: ";
				this.SPASSED = 0;
				egl.atLine(this.eze$$fileName,21,755,1, this);
				this.SPASSED = 0;
				this.SFAILED = 0;
				egl.atLine(this.eze$$fileName,22,780,1, this);
				this.SFAILED = 1;
				this.SERROR = 0;
				egl.atLine(this.eze$$fileName,23,804,1, this);
				this.SERROR = 2;
				this.SNOT_RUN = 0;
				egl.atLine(this.eze$$fileName,24,830,1, this);
				this.SNOT_RUN = 3;
				this.SBAD = 0;
				egl.atLine(this.eze$$fileName,25,852,1, this);
				this.SBAD = 4;
			}
			,
			"eze$$setInitial": function() {
				if(egl.org.eclipse.edt.eunit.runtime.ConstantsLib['$inst']) return egl.org.eclipse.edt.eunit.runtime.ConstantsLib['$inst'];
				egl.org.eclipse.edt.eunit.runtime.ConstantsLib['$inst']=this;
				try { egl.enter("<init>",this,arguments);
					this.eze$$setEmpty();
					if (!egl.debugg) egl.leave();
				} finally {
					if (!egl.debugg){
					} else { egl.leave(); }
				}
			}
			,
			"eze$$getAnnotations": function() {
				if(this.annotations === undefined){
					this.annotations = {};
					this.annotations["XMLRootElement"] = new egl.eglx.xml.binding.annotation.XMLRootElement("ConstantsLib", null, false);
				}
				return this.annotations;
			}
			,
			"eze$$getFieldInfos": function() {
				if(this.fieldInfos === undefined){
					var annotations;
					this.fieldInfos = new Array();
				}
				return this.fieldInfos;
			}
			,
			"getNEWLINE": function() {
				return NEWLINE;
			}
			,
			"getEXIT_PREFIX": function() {
				return EXIT_PREFIX;
			}
			,
			"getSPASSED": function() {
				return SPASSED;
			}
			,
			"getSFAILED": function() {
				return SFAILED;
			}
			,
			"getSERROR": function() {
				return SERROR;
			}
			,
			"getSNOT_RUN": function() {
				return SNOT_RUN;
			}
			,
			"getSBAD": function() {
				return SBAD;
			}
			,
			"toString": function() {
				return "[ConstantsLib]";
			}
			,
			"eze$$getName": function() {
				return "ConstantsLib";
			}
			,
			"eze$$getChildVariables": function() {
				var eze$$parent = this;
				return [
				{name: "NEWLINE", value : eze$$parent.NEWLINE, type : "eglx.lang.EString", jsName : "NEWLINE"},
				{name: "EXIT_PREFIX", value : eze$$parent.EXIT_PREFIX, type : "eglx.lang.EString", jsName : "EXIT_PREFIX"},
				{name: "SPASSED", value : eze$$parent.SPASSED, type : "eglx.lang.EInt", jsName : "SPASSED"},
				{name: "SFAILED", value : eze$$parent.SFAILED, type : "eglx.lang.EInt", jsName : "SFAILED"},
				{name: "SERROR", value : eze$$parent.SERROR, type : "eglx.lang.EInt", jsName : "SERROR"},
				{name: "SNOT_RUN", value : eze$$parent.SNOT_RUN, type : "eglx.lang.EInt", jsName : "SNOT_RUN"},
				{name: "SBAD", value : eze$$parent.SBAD, type : "eglx.lang.EInt", jsName : "SBAD"}
				];
			}
		}
	);
