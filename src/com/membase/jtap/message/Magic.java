package com.membase.jtap.message;

public enum Magic {
	/**
	 * Defines a binary tap packet
	 */
	PROTOCOL_BINARY_REQ((byte) 0x80);

	/**
	 * The magic value
	 */
	public byte magic;

	/**
	 * Defines the magic value
	 * @param magic - The new magic value
	 */
	Magic(byte magic) {
		this.magic = magic;
	}
}
