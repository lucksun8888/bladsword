package com.event;

public interface GameEvent {
	
	public String init() throws Exception;
	public String process() throws Exception;
	public String end() throws Exception;
	
}
