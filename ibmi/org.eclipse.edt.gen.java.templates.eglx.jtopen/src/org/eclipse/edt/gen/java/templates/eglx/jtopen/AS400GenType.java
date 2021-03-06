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
package org.eclipse.edt.gen.java.templates.eglx.jtopen;

import org.eclipse.edt.gen.java.Context;
import org.eclipse.edt.gen.java.templates.JavaTemplate;
import org.eclipse.edt.gen.java.templates.JavaTemplate.TypeNameKind;
import org.eclipse.edt.gen.java.templates.eglx.lang.TimestampTypeTemplate;
import org.eclipse.edt.mof.codegen.api.TabbedWriter;
import org.eclipse.edt.mof.egl.Annotation;
import org.eclipse.edt.mof.egl.ArrayType;
import org.eclipse.edt.mof.egl.Expression;
import org.eclipse.edt.mof.egl.FixedPrecisionType;
import org.eclipse.edt.mof.egl.Handler;
import org.eclipse.edt.mof.egl.LogicAndDataPart;
import org.eclipse.edt.mof.egl.Member;
import org.eclipse.edt.mof.egl.Record;
import org.eclipse.edt.mof.egl.SequenceType;
import org.eclipse.edt.mof.egl.TimestampType;
import org.eclipse.edt.mof.egl.Type;
import org.eclipse.edt.mof.egl.utils.TypeUtils;

public class AS400GenType {

	static AS400GenType INSTANCE = new AS400GenType();
	
	public void genAS400Type(Member member, Type type, Context ctx, TabbedWriter out){
		out.print("new ");
		Annotation annot = org.eclipse.edt.gen.CommonUtilities.getAnnotation(member, Constants.signature_StructBin1, ctx);
		if((annot = org.eclipse.edt.gen.CommonUtilities.getAnnotation(member, Constants.signature_StructBin1, ctx)) != null){
			out.print("org.eclipse.edt.java.jtopen.access.AS400Bin1()");
		}
		else if((annot = org.eclipse.edt.gen.CommonUtilities.getAnnotation(member, Constants.signature_StructBin2, ctx)) != null){
			genAS400Bin2(out);
		}
		else if((annot = org.eclipse.edt.gen.CommonUtilities.getAnnotation(member, Constants.signature_StructBin4, ctx)) != null){
			genAS400Bin4(out);
		}
		else if((annot = org.eclipse.edt.gen.CommonUtilities.getAnnotation(member, Constants.signature_StructBin8, ctx)) != null){
			genAS400Bin8(out);
		}
		else if((annot = org.eclipse.edt.gen.CommonUtilities.getAnnotation(member, Constants.signature_StructUnsignedBin1, ctx)) != null){
			out.print("com.ibm.as400.access.AS400UnsignedBin1()");
		}
		else if((annot = org.eclipse.edt.gen.CommonUtilities.getAnnotation(member, Constants.signature_StructUnsignedBin2, ctx)) != null){
			out.print("com.ibm.as400.access.AS400UnsignedBin2()");
		}
		else if((annot = org.eclipse.edt.gen.CommonUtilities.getAnnotation(member, Constants.signature_StructUnsignedBin4, ctx)) != null){
			out.print("com.ibm.as400.access.AS400UnsignedBin4()");
		}
		else if((annot = org.eclipse.edt.gen.CommonUtilities.getAnnotation(member, Constants.signature_StructUnsignedBin8, ctx)) != null){
			out.print("org.eclipse.edt.java.jtopen.access.AS400UnsignedBin8()");
		}
		else if((annot = org.eclipse.edt.gen.CommonUtilities.getAnnotation(member, Constants.signature_StructZonedDecimal, ctx)) != null){
			out.print("com.ibm.as400.access.AS400ZonedDecimal(");
			genLength(type, out, annot);
			out.print(", ");
			genDecimals(type, out, annot);
			out.print(")");
		}
		else if((annot = org.eclipse.edt.gen.CommonUtilities.getAnnotation(member, Constants.signature_StructDecFloat, ctx)) != null){
			out.print("com.ibm.as400.access.AS400DecFloat(");
			if(annot.getValue(Constants.subKey_length) != null){
				out.print(((Integer)annot.getValue(Constants.subKey_length)).toString());
			}
			else{
				out.print("unknown");
			}
			out.print(")");
		}
		else if((annot = org.eclipse.edt.gen.CommonUtilities.getAnnotation(member, Constants.signature_StructPackedDecimal, ctx)) != null){
			genAS400PackedDecimal(type, ctx, out, annot);
		}
		else if((annot = org.eclipse.edt.gen.CommonUtilities.getAnnotation(member, Constants.signature_StructFloat4, ctx)) != null){
			genAS400Float4(out);
		}
		else if((annot = org.eclipse.edt.gen.CommonUtilities.getAnnotation(member, Constants.signature_StructFloat8, ctx)) != null){
			genAS400Float8(out);
		}
		else if((annot = org.eclipse.edt.gen.CommonUtilities.getAnnotation(member, Constants.signature_StructDate, ctx)) != null){
			genAS400Date(member, type, annot, ctx, out);
		}
		else if((annot = org.eclipse.edt.gen.CommonUtilities.getAnnotation(member, Constants.signature_StructText, ctx)) != null){
			genAS400Text(member, type, annot, ctx, out);
		}
		else if((annot = org.eclipse.edt.gen.CommonUtilities.getAnnotation(member, Constants.signature_StructTimestamp, ctx)) != null){
			genAS400Timestamp(type, ctx, out, annot);
		}
		else if((annot = org.eclipse.edt.gen.CommonUtilities.getAnnotation(member, Constants.signature_StructTime, ctx)) != null){
			genAS400Time(member, type, annot, ctx, out);
		}
		else if((annot = org.eclipse.edt.gen.CommonUtilities.getAnnotation(member, Constants.signature_StructByteArray, ctx)) != null){
			genAS400ByteArray(member, type, annot, ctx, out);
		}
		else if(type instanceof ArrayType &&
				(annot = org.eclipse.edt.gen.CommonUtilities.getAnnotation(member, Constants.signature_StructArray, ctx)) != null){
			out.print("org.eclipse.edt.java.jtopen.access.AS400Array(");
			if(annot != null){
				org.eclipse.edt.gen.CommonUtilities.addGeneratorAnnotation(member, (Annotation)annot.getValue(Constants.subKey_elementTypeAnnotation), ctx);
			}
			genAS400Type(member, ((ArrayType)type).getElementType(), ctx, out);
			out.print(", ");
			ctx.invoke(JavaTemplate.genRuntimeTypeName, ((ArrayType)type).getElementType(), ctx, out, TypeNameKind.EGLImplementation);
			out.print(".class, ");
			Integer elementCount = null;
			if(annot != null){
				elementCount = (Integer)annot.getValue(Constants.subKey_elementCount);
			}
			if(elementCount != null){
				out.print(elementCount.toString());
			}
			else{
				out.print("unknown");
			}
			out.print(")");
		}
		else if (type.getClassifier().equals(TypeUtils.Type_BIGINT)){
			genAS400Bin8(out);
		}
		else if (type.getClassifier().equals(TypeUtils.Type_DATE)){
			genAS400Date(member, type, null, ctx, out);
		}
		else if (type.getClassifier().equals(TypeUtils.Type_DECIMAL)){
			genAS400PackedDecimal(type, ctx, out, annot);
		}
		else if (type.getClassifier().equals(TypeUtils.Type_FLOAT)){
			genAS400Float8(out);
		}
		else if (type.getClassifier().equals(TypeUtils.Type_INT)){
			genAS400Bin4(out);
		}
		else if (type.getClassifier().equals(TypeUtils.Type_SMALLFLOAT)){
			genAS400Float4(out);
		}
		else if (type.getClassifier().equals(TypeUtils.Type_SMALLINT)){
			genAS400Bin2(out);
		}
		else if (type.getClassifier().equals(TypeUtils.Type_STRING)){
			genAS400Text(member, type, null, ctx, out);
		}
		else if (type.getClassifier().equals(TypeUtils.Type_TIMESTAMP)){
			genAS400Timestamp(type, ctx, out, annot);
		}
		else if (type.getClassifier().equals(TypeUtils.Type_TIME)){
			genAS400Time(member, type, null, ctx, out);
		}
		else if (type.getClassifier().equals(TypeUtils.Type_BYTES)){
			genAS400ByteArray(member, type, null, ctx, out);
		}
		else if (type instanceof Record){
			genAS400Structure((LogicAndDataPart)type, out);
		}
		else if (type instanceof Handler){
			genAS400Structure((LogicAndDataPart)type, out);
		}
		else{
			out.print("unknown");
		}

	}
	private void genAS400ByteArray(Member member, Type type, Annotation annot, Context ctx, TabbedWriter out) {
		out.print("com.ibm.as400.access.AS400ByteArray(");
		genLength(type, out, annot);
		out.print(")");
	}
	private void genAS400Time(Member member, Type type, Annotation annot, Context ctx, TabbedWriter out) {
		out.print("org.eclipse.edt.java.jtopen.access.AS400Time(");
		genAS400AnnotationIBMiFormat(annot, ctx, out);
		genAS400AnnotationIBMiSeparatorChar(annot, ctx, out);
		genTimezone(out, annot);
		out.print(", ");
		out.print(Constants.as400ConnectionName);
		out.print(")");
	}
	private void genAS400Date(Member member, Type type, Annotation annot, Context ctx, TabbedWriter out) {
		out.print("org.eclipse.edt.java.jtopen.access.AS400Date(");
		genAS400AnnotationIBMiFormat(annot, ctx, out);
		genAS400AnnotationIBMiSeparatorChar(annot, ctx, out);
		genTimezone(out, annot);
		out.print(", ");
		out.print(Constants.as400ConnectionName);
		out.print(")");
	}
	private void genAS400AnnotationIBMiSeparatorChar(Annotation annot, Context ctx, TabbedWriter out){
		String ibmiSeperator = annot == null ? "" : (String)annot.getValue(Constants.subKey_ibmiSeparatorChar);
		if(ibmiSeperator != null){
			out.print(", \"");
			out.print(ibmiSeperator);
			out.print("\"");
		}
		else{
			out.print(", null");
		}
	}
	private void genAS400AnnotationIBMiFormat(Annotation annot, Context ctx, TabbedWriter out){
		Expression ibmiFormat = annot == null ? null : (Expression)annot.getValue(Constants.subKey_ibmiFormat);
		if(ibmiFormat != null){
			ctx.invoke(org.eclipse.edt.gen.java.templates.JavaTemplate.genExpression, ibmiFormat, ctx, out);
		}
		else{
			out.print("null");
		}
	}
	private void genAS400Timestamp(Type type, Context ctx, TabbedWriter out, Annotation annot) {
		out.print("org.eclipse.edt.java.jtopen.access.AS400Timestamp(null,");
		genTimestampPattern(type, ctx, out, annot);
		genTimezone(out, annot);
		out.print(", ");
		out.print(Constants.as400ConnectionName);
		out.print(")");
	}
	private void genTimezone(TabbedWriter out, Annotation annot) {
		String timeZone = annot == null ? null : (String)annot.getValue(Constants.subKey_timeZoneID);
		if(timeZone != null && !timeZone.isEmpty()){
			out.print(", \"");
			out.print(timeZone);
			out.print("\"");
		}
		else{
			out.print(", null");
		}
	}
	private void genAS400Text(Member member, Type type, Annotation annot, Context ctx, TabbedWriter out) {
		out.print("org.eclipse.edt.java.jtopen.access.AS400Text(");
		genLength(type, out, annot);
		out.print(", ");
		String encoding = annot == null ? null : (String)annot.getValue(Constants.subKey_encoding);
		if(encoding != null && !encoding.isEmpty()){
			out.print("\"");
			out.print(encoding);
			out.print("\"");
		}
		else{
			out.print(Constants.as400ConnectionName);
		}
		out.print(", ");
		Boolean preserveTrailingSpaces = annot == null ? null :(Boolean)annot.getValue(Constants.subKey_preserveTrailingSpaces);
		if(preserveTrailingSpaces != null){
			out.print(preserveTrailingSpaces.toString());
		}
		else{
			out.print("false");
		}
		out.print(")");
	}
	private void genAS400Float8(TabbedWriter out) {
		out.print("com.ibm.as400.access.AS400Float8()");
	}
	private void genAS400Float4(TabbedWriter out) {
		out.print("com.ibm.as400.access.AS400Float4()");
	}
	private void genAS400PackedDecimal(Type type, Context ctx,
			TabbedWriter out, Annotation annot) {
		out.print("com.ibm.as400.access.AS400PackedDecimal(");
		genLength(type, out, annot);
		out.print(", ");
		genDecimals(type, out, annot);
		out.print(")");
	}
	private void genAS400Bin2(TabbedWriter out) {
		out.print("com.ibm.as400.access.AS400Bin2()");
	}
	private void genAS400Bin4(TabbedWriter out) {
		out.print("com.ibm.as400.access.AS400Bin4()");
	}
	private void genAS400Bin8(TabbedWriter out) {
		out.print("com.ibm.as400.access.AS400Bin8()");
	}
	
	public void genAS400Structure(LogicAndDataPart type, TabbedWriter out){
		AS400GenHelper.genHelperClassName(type, out);
		out.print("().getAS400Structure(");
		out.print(Constants.as400ConnectionName);
		out.print(")");
	}
	private void genDecimals(Type type, TabbedWriter out, Annotation typeAnnot) {
		if(type instanceof FixedPrecisionType){
			out.print(((FixedPrecisionType)type).getDecimals());
		}
		else if(typeAnnot != null && typeAnnot.getValue(Constants.subKey_decimals) != null){
			out.print(((Integer)typeAnnot.getValue(Constants.subKey_decimals)).toString());
		}
		else {
			out.print("unknown");
		}
	}
	
	private void genLength(Type type, TabbedWriter out, Annotation typeAnnot) {
		if(type instanceof FixedPrecisionType){
			out.print(((FixedPrecisionType)type).getLength());
		}
		else if(type instanceof SequenceType){
			out.print(((SequenceType)type).getLength());
		}
		else if(type instanceof SequenceType){
			out.print(((SequenceType)type).getLength());
		}
		else if(typeAnnot != null && typeAnnot.getValue(Constants.subKey_length) != null){
			out.print(((Integer)typeAnnot.getValue(Constants.subKey_length)).toString());
		}
		else {
			out.print("unknown");
		}
	}
	private void genTimestampPattern(Type type, Context ctx, TabbedWriter out, Annotation typeAnnot){
		String eglPattern = typeAnnot == null ? null : (String)typeAnnot.getValue(Constants.subKey_eglPattern);
		if(type instanceof TimestampType){
			new TimestampTypeTemplate().generateOptions((TimestampType)type, ctx, out);
		}
		else if(eglPattern != null && !eglPattern.isEmpty()){
			new TimestampTypeTemplate().generateOptions(type, ctx, out, eglPattern);
		}
		else {
			out.print("unknown, unknown");
		}
	}	
}
