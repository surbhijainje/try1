package com.rest.pojo.complexcollectiondata;

public class CollectionRoot {
	
	Collection collection;
	
	
	public CollectionRoot() {
		
	}

	
	public CollectionRoot(Collection collection) {
		super();
		this.collection = collection;
	}

	public Collection getCollection() {
		return collection;
	}

	public void setCollection(Collection collection) {
		this.collection = collection;
	}

}
