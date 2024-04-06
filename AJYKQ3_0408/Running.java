package com.msc;

import java.util.List;
import java.util.Scanner;

public class Running {

	public static void main(String[] args) {

		Dao dao = new Dao();
		
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Szeretn�l hozz�adni vev� adatot? (i/n): ");
        
        String decision = sc.nextLine();
        
        if(decision.contains("i")) {
		
			// Vev� hozz�ad�sa
        	
        	System.out.println("K�rlek add meg az al�bbi adatokat: ");
            System.out.print("Vev�ID: ");
            int vevoID = sc.nextInt();
            sc.nextLine();

            System.out.print("N�v: ");
            String nev = sc.nextLine();

            System.out.print("Nem: ");
            String nem = sc.nextLine();

            System.out.print("Telefon: ");
            String telefon = sc.nextLine();

			Vevo vevo = new Vevo();
			
			vevo.setVevoID(vevoID);
			vevo.setNev(nev);
			vevo.setNem(nem);
			vevo.setTelefon(telefon);
			dao.saveCustomer(vevo);
			
        }
		
        
        
        
		// Vev� adatainak lek�r�se
		
        System.out.println("Vev� adatok:\n");
		
		List<Vevo> vevok = dao.getAllCustomerData();
		for(Vevo v : vevok) {
			System.out.println("Vev�ID: " + v.getVevoID() + ", N�v: " + v.getNev() +
					", Nem: " + v.getNem() + ", Telefonsz�m: " + v.getTelefon());
		}
		

        System.out.print("Szeretn�l hozz�adni mozi adatot? (i/n): ");
        
        decision = sc.nextLine();
        
        if(decision.contains("i")) {
		
			// Mozi hozz�ad�sa
        	
        	System.out.println("K�rlek add meg az al�bbi adatokat: ");
            System.out.print("MoziID: ");
            int moziID = sc.nextInt();
            sc.nextLine();

            System.out.print("Mozi neve: ");
            String mNev = sc.nextLine();

            System.out.print("Hely: ");
            String hely = sc.nextLine();


        	
			Mozi cinema = new Mozi();
			
			
			cinema.setMoziID(moziID);
			cinema.setmNev(mNev);
			cinema.setHely(hely);
			dao.saveCinema(cinema);
			
        }
        
        System.out.print("Szeretn�l �sszek�tni egy vev�t egy mozival? (i/n): ");
        
        decision = sc.nextLine();
        
        if(decision.contains("i")) {
        
            System.out.print("Melyik vev�t szeretn�d hozz�adni a kapcsolathoz? (Vev�ID): ");
            
            int vevoid = sc.nextInt();
            sc.nextLine();
            
            System.out.print("Melyik mozihoz szeretn�l kapcsol�dni? (MoziID): ");
            
            int moziid = sc.nextInt();
            sc.nextLine();

            VM vm = new VM();
            vm.setMoziIDVM(moziid);
            vm.setVevoIDVM(vevoid);
            
            dao.saveRelationship(vm);
        
        }
		
		// getAllCinemaData
		
        System.out.println("Az �sszes mozi adatai:\n");
		
		List<Mozi> cinemas = dao.getAllCinemaData();
		for(Mozi m : cinemas) {
			System.out.println("MoziID: " + m.getMoziID() + ", mozi neve: " + m.getmNev() +
					", mozi helye: " + m.getHely());
		}
		
		// getAllRelationships
		
        
        System.out.println("Minden vev� kapcsolata a mozikhoz:\n");
		
		List<VM> vmList = dao.findAllCinemaCustomerRelationships();
		for (VM vm : vmList) {
		    if (vm != null) {
		        System.out.println("Mozi: " + vm.getMoziIDVM() + ", Vev�: " + vm.getVevoIDVM());
		    } else {
		        System.out.println("Null VM objektum!");
		    }
		}
		
		// Minden mozi ki�r�sa, amelynek van legal�bb egy kapcsolatat
		
        System.out.println("Minden mozi, amelynek legal�bb egy kapcsolata van:\n");
		
		List<Mozi> moziList = dao.findCinemasWithCustomers();
		for (Mozi m : moziList) {
			System.out.println("MoziID: " + m.getMoziID() + ", mozi neve: " + m.getmNev() +
					", mozi helye: " + m.getHely());
		}
		
		// Mozi t�rl�se
		
        System.out.print("Szeretn�l t�r�lni egy mozit? (i/n): ");
        
        String delete = sc.nextLine();
        
        if(delete.contains("i")) {
        
	        System.out.print("K�rlek add meg a MoziID-t: ");
	        int id = sc.nextInt();
	
	        dao.deleteCinemaRelationshipById(id);
	        dao.deleteCinemaById(id);
	
	        System.out.println("A(z)" + id + " ID Mozi sikeresen t�r�lve.");
	        
	        sc.close();
	        
	        // getAllCinemaData
			
	        System.out.println("�sszes mozi adata:\n");
			
			cinemas = dao.getAllCinemaData();
			for(Mozi m : cinemas) {
				System.out.println("MoziID: " + m.getMoziID() + ", mozi neve: " + m.getmNev() +
						", mozi helye: " + m.getHely());
			}
        }
        
        System.out.println("----------------------------------------");

	}

}