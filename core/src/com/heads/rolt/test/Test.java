package com.heads.rolt.test;

import com.heads.rolt.characters.Archer;
import com.heads.rolt.characters.Healer;
import com.heads.rolt.characters.Mage;
import com.heads.rolt.characters.Warrior;

public class Test {

	public static void main(String[] args) {
		Warrior w1 = new Warrior(2, 10, 8, 10, 1, null);
		Archer a1 = new Archer(8, 2, 4, 4, 6, null);
		Mage m1 = new Mage(6, 0, 4, 6, 4, null);
		Healer h1 = new Healer(5, 0, 8, 2, 5, null);
		
		System.out.println(w1);
		System.out.println(a1);
		System.out.println(m1);
		System.out.println(h1);
	}

}
