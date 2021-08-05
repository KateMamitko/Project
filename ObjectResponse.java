package com.example.project.pojo;

import com.google.gson.annotations.SerializedName;

public class ObjectResponse {
	@SerializedName("data")
	private long id;
	@SerializedName("type")
	private String type;
	@SerializedName("url")
	private String url;
	@SerializedName("message")
	private String tile;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTile() {
		return tile;
	}

	public void setTile(String tile) {
		this.tile = tile;
	}
}
