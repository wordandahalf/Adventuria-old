package io.github.wordandahalf.adventuria.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class BiKeyHashMap<K1, K2, V> {
	private final HashMap<K1, HashMap<K2, V>> hashMap;
	
	public BiKeyHashMap() {
		this.hashMap = new HashMap<>();
	}
	
	public void remove(K1 key1, K2 key2) {
		if(!this.hashMap.containsKey(key1))
			this.hashMap.put(key1, new HashMap<>());
		
		this.hashMap.get(key1).remove(key2);
	}
	
	public void put(K1 key1, K2 key2, V value) {
		if(!this.hashMap.containsKey(key1))
			this.hashMap.put(key1, new HashMap<>());
		
		this.hashMap.get(key1).put(key2, value);
	}
	
	public V get(K1 key1, K2 key2) {
		if(!this.hashMap.containsKey(key1))
			this.hashMap.put(key1, new HashMap<>());
		
		return this.hashMap.get(key1).get(key2);
	}
	
	public Set<Pair<K1, K2>> keySet() {
		HashSet<Pair<K1, K2>> keys = new HashSet<>();
		
		for(K1 key1 : this.hashMap.keySet()) {
			for(K2 key2 : this.hashMap.get(key1).keySet()) {
				keys.add(new Pair<>(key1, key2));
			}
		}
		
		return (Set<Pair<K1, K2>>) keys;
	}
	
	public Collection<V> values() {
		ArrayList<V> values = new ArrayList<>();
		
		hashMap.values().iterator().forEachRemaining((arg) -> {
			values.addAll(arg.values());
		});
		
		return values;
	}
}
