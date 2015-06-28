package net.adventuria.errorHandler;

import java.io.IOException;

public class WorldSaveException extends IOException
{
	private static final long serialVersionUID = 8122130406217855981L;

	private int type = 0;
	
	/**
	 * Types:
	 * 0: Could not delete save file
	 * 1: Could not create save file
	 * 2: Could not write to save file
	 */
	public WorldSaveException(int type)
	{
		this.type = type;
	}
	
	public int getType()
	{
		return this.type;
	}
	
	@Override
	public void printStackTrace()
	{
		String reason = getType() == 0 ? "There was an unexpected error on deleting the existing save file. Please delete it, and hit the \'F\' key" : getType() == 1 ? "There was an unexpected error on creating a new save file. Please hit the \'F\' key again, and if the problem persists, try deleting the save file." : "There was an unexpected error on writing to the save file. Please hit the \'F\' key again, and if the problem persists, please delete the save file.";
		
		System.err.println(reason);
	}
}
