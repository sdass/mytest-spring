package com.subra;

public class Dummy {
		private String title;
		private String value;
		
		public Dummy(){}
		public Dummy(String title, String value) {
			super();
			this.title = title;
			this.value = value;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		@Override
		public String toString() {
			return "Dummy [title=" + title + ", value=" + value + "]";
		}
		
		
	}
	
