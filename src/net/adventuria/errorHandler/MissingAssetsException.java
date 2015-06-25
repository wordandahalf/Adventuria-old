package net.adventuria.errorHandler;

import java.io.IOException;

public class MissingAssetsException extends IOException {
	private static final long serialVersionUID = -1598907361419017564L;

	/**
	 * Types: 0 = Failed to load (during init) 1 = Failed to load (during
	 * gameplay)
	 */
	private int type = 0;

	public MissingAssetsException(int type) {
		this.type = type;
	}

	public int getType() {
		return this.type;
	}

	@Override
	public void printStackTrace() {
		String reason = getType() == 0 ? "Assets could not be read at startup!"
				: "Assets were removed after startup!";

		System.err.println("net.adventura.MissingAssetsException: " + reason
				+ " Please replace the missing or removed files!");
	}
}
