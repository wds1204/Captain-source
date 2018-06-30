package com.captain.wds.degsinpattern.builder;

/**
 * Created by wds on 2018/4/4.
 */

public class BMWBuilder extends Builder {

	private BMW bmw = new BMW();

	private int a=1;
	private int b=2;

	private int ba=3;
	private int bb=4;

	public BMWBuilder a(int a) {
		this.a = a;
		return this;
	}

	public BMWBuilder b(int b) {
		this.b = b;
		return this;
	}
	public BMWBuilder ba(int ba) {
		this.ba =ba;
		return this;
	}

	public BMWBuilder bb(int bb) {
		this.bb = bb;
		return this;
	}

	@Override public Car createrBall() {
		return bmw;
	}
}
