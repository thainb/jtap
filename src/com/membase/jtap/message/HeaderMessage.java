/*
 * Copyright (c) 2010 Membase. All Rights Reserved.
 */

package com.membase.jtap.message;

import java.nio.ByteBuffer;

import com.membase.jtap.internal.Util;

/**
 * The HeaderMessage implements the header of a tap message. It should not be used to
 * create tap messages that have a body. 
 */
public class HeaderMessage {
	public static final int MAGIC_INDEX = 0;
	public static final int MAGIC_FIELD_LENGTH = 1;
	public static final int OPCODE_INDEX = 1;
	public static final int OPCODE_FIELD_LENGTH = 1;
	public static final int KEY_LENGTH_INDEX = 2;
	public static final int KEY_LENGTH_FIELD_LENGTH = 2;
	public static final int EXTRA_LENGTH_INDEX = 4;
	public static final int EXTRA_LENGTH_FIELD_LENGTH = 1;
	public static final int DATA_TYPE_INDEX = 5;
	public static final int DATA_TYPE_FIELD_LENGTH = 1;
	public static final int VBUCKET_INDEX = 6;
	public static final int VBUCKET_FIELD_LENGTH = 2;
	public static final int TOTAL_BODY_INDEX = 8;
	public static final int TOTAL_BODY_FIELD_LENGTH = 4;
	public static final int OPAQUE_INDEX = 12;
	public static final int OPAQUE_FIELD_LENGTH = 4;
	public static final int CAS_INDEX = 16;
	public static final int CAS_FIELD_LENGTH = 8;
	public static final int HEADER_LENGTH = 24;
	
	protected byte[] mbytes;

	protected HeaderMessage() {
		mbytes = new byte[HEADER_LENGTH];
	}
	
	public final void setMagic(Magic m) {
		mbytes[MAGIC_INDEX] = (byte) m.magic;
	}
	
	public final int getMagic() {
		return mbytes[MAGIC_INDEX];
	}
	
	public final void setOpcode(Opcode o) {
		mbytes[OPCODE_INDEX] = (byte) o.opcode;
	}
	
	public final byte getOpcode() {
		return mbytes[OPCODE_INDEX];
	}
	
	protected final void setKeylength(long l) {
		Util.longToField(mbytes, KEY_LENGTH_INDEX, KEY_LENGTH_FIELD_LENGTH, l);
	}
	
	public final int getKeylength() {
		return (int) Util.fieldToLong(mbytes, KEY_LENGTH_INDEX, KEY_LENGTH_FIELD_LENGTH);
	}
	
	public final void setDatatype(byte b) {
		mbytes[DATA_TYPE_INDEX] = b;
	}
	
	public final byte getDatatype() {
		return mbytes[DATA_TYPE_INDEX];
	}
	
	public final void setExtralength(int i) {
		mbytes[EXTRA_LENGTH_INDEX] = (byte) i;
	}
	
	public final int getExtralength() {
		return mbytes[EXTRA_LENGTH_INDEX];
	}
	
	public final void setVbucket(int vb) {
		Util.longToField(mbytes, VBUCKET_INDEX, VBUCKET_FIELD_LENGTH, vb);
	}
	
	public final int getVbucket() {
		return (int) Util.fieldToLong(mbytes, VBUCKET_INDEX, VBUCKET_FIELD_LENGTH);
	}
	
	public final void setTotalbody(long l) {
		Util.longToField(mbytes, TOTAL_BODY_INDEX, TOTAL_BODY_FIELD_LENGTH, l);
	}
	
	public final int getTotalbody() {
		return (int) Util.fieldToLong(mbytes, TOTAL_BODY_INDEX, TOTAL_BODY_FIELD_LENGTH);
	}
	
	public final void setOpaque(int op) {
		Util.longToField(mbytes, OPAQUE_INDEX, OPAQUE_FIELD_LENGTH, op);
	}
	
	public final int getOpaque() {
		return (int) Util.fieldToLong(mbytes, OPAQUE_INDEX, OPAQUE_FIELD_LENGTH);
	}
	
	public final void setCas(long cas) {
		Util.longToField(mbytes, CAS_INDEX, CAS_FIELD_LENGTH, cas);
	}
	
	public final long getCas() {
		return Util.fieldToLong(mbytes, CAS_INDEX, CAS_FIELD_LENGTH);
	}
	
	public final int getMessageLength() {
		return HEADER_LENGTH + getTotalbody();
	}
	
	public final ByteBuffer getBytes() {
		return ByteBuffer.wrap(mbytes);
	}
	
	public void printMessage() {
		int colNum = 0;
		System.out.printf("   %5s%5s%5s%5s\n", "0", "1", "2", "3");
		System.out.print("   ----------------------");
		for (int i = 0; i < mbytes.length; i++) {
			if ((i % 4) == 0) {
				System.out.printf("\n%3d|", colNum);
				colNum += 4;
			}
			int field = mbytes[i];
			if (field < 0)
				field = field + 256;
			System.out.printf("%5x", field);
		}
		System.out.print("\n\n");
	}
	
	public void printMessageDetails() {
		System.out.println("----------Message----------");
		System.out.printf("Magic: 0x%x\n", mbytes[MAGIC_INDEX]);
		System.out.printf("Opcode: 0x%x\n", mbytes[OPCODE_INDEX]);
		System.out.printf("Key Length: %d\n", getKeylength());
		System.out.printf("Extra Length: %d\n", getExtralength());
		System.out.printf("Data Type: 0x%x\n", getDatatype());
		System.out.printf("VBucket: %d\n", getVbucket());
		System.out.printf("Total Body: %d\n", getTotalbody());
		System.out.printf("Opaque: %d\n", getOpaque());
		System.out.printf("CAS: %d\n", getCas());
	}
}
