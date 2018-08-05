package io.github.wordandahalf.adventuria.utils;

public class Pair<Left, Right> {
	public final Left left;
	public final Right right;
	
	public Pair(Left left, Right right) {
		this.left = left;
		this.right = right;
	}
	
	@Override
	public String toString() {
		return "(" + this.left + ", " + this.right + ")";
	}
	
	public Left getLeft() { return this.left; }
	public Right getRight() { return this.right; }
	
	public static <Left, Right> Pair<Left, Right> of(Left left, Right right) {
		return new Pair<Left, Right>(left, right);
	}
}
