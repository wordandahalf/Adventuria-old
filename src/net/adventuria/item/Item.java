package net.adventuria.item;

import java.awt.Rectangle;

public class Item extends Rectangle {
	private static final long serialVersionUID = 3040720615901264104L;

	private ItemID ID = ItemID.NULL;

	private int Count = 0;

	public Item(ItemID ID, int Count) {
		this.ID = ID;
		this.Count = Count;
	}

	public ItemID getID() {
		return this.ID;
	}

	public int getCount() {
		return this.Count;
	}

	public void setCount(int Count) {
		this.Count = Count;
	}

	public void destroy() {
		this.ID = ItemID.NULL;
		this.Count = -1;
	}
}
