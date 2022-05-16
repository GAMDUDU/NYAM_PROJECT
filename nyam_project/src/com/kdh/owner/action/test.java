package com.kdh.owner.action;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("??");
		
		int allv=13;
		int allp=5;
		int v=7;
		int page=0;
		int i =13;
		int d =0;
		

		while(i>=v) {
			if (d%allp==0) {
				page++;
				
			}
			d++;
			i--;
		}
		
		int num = 4-page;
		
		
		System.out.println(page);
		
		
		

	}

}
