package com.membase.jtap.message;

public enum Opcode {
	NOOP((byte) 0x0a),
	SASLLIST((byte) 0x20),
	SASLAUTH((byte) 0x21),
	REQUEST((byte) 0x40),
	MUTATION((byte) 0x41),
	DELETE((byte) 0x42),
	FLUSH((byte) 0x43),
	OPAQUE((byte)0x44),
	VBUCKETSET((byte) 0x45);

	public byte opcode;

	Opcode(byte opcode) {
		this.opcode = opcode;
	}
}
