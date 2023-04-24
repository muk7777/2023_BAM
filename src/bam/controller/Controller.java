package bam.controller;

import bam.dto.Member;

public abstract class Controller {
	public abstract void doAction(String cmd, String methodName);
	public abstract void makeTestData();
	public static Member loginedMember;
}
