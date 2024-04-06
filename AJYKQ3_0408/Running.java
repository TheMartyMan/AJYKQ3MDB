package com.msc;

import java.util.List;
import java.util.Scanner;

public class Running {

	public static void main(String[] args) {

		Dao dao = new Dao();
		
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Szeretnél hozzáadni vevõ adatot? (i/n): ");
        
        String decision = sc.nextLine();
        
        if(decision.contains("i")) {
		
			// Vevõ hozzáadása
        	
        	System.out.println("Kérlek add meg az alábbi adatokat: ");
            System.out.print("VevõID: ");
            int vevoID = sc.nextInt();
            sc.nextLine();

            System.out.print("Név: ");
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
		
        
        
        
		// Vevõ adatainak lekérése
		
        System.out.println("Vevõ adatok:\n");
		
		List<Vevo> vevok = dao.getAllCustomerData();
		for(Vevo v : vevok) {
			System.out.println("VevõID: " + v.getVevoID() + ", Név: " + v.getNev() +
					", Nem: " + v.getNem() + ", Telefonszám: " + v.getTelefon());
		}
		

        System.out.print("Szeretnél hozzáadni mozi adatot? (i/n): ");
        
        decision = sc.nextLine();
        
        if(decision.contains("i")) {
		
			// Mozi hozzáadása
        	
        	System.out.println("Kérlek add meg az alábbi adatokat: ");
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
        
        System.out.print("Szeretnél összekötni egy vevõt egy mozival? (i/n): ");
        
        decision = sc.nextLine();
        
        if(decision.contains("i")) {
        
            System.out.print("Melyik vevõt szeretnéd hozzáadni a kapcsolathoz? (VevõID): ");
            
            int vevoid = sc.nextInt();
            sc.nextLine();
            
            System.out.print("Melyik mozihoz szeretnél kapcsolódni? (MoziID): ");
            
            int moziid = sc.nextInt();
            sc.nextLine();

            VM vm = new VM();
            vm.setMoziIDVM(moziid);
            vm.setVevoIDVM(vevoid);
            
            dao.saveRelationship(vm);
        
        }
		
		// getAllCinemaData
		
        System.out.println("Az összes mozi adatai:\n");
		
		List<Mozi> cinemas = dao.getAllCinemaData();
		for(Mozi m : cinemas) {
			System.out.println("MoziID: " + m.getMoziID() + ", mozi neve: " + m.getmNev() +
					", mozi helye: " + m.getHely());
		}
		
		// getAllRelationships
		
        
        System.out.println("Minden vevõ kapcsolata a mozikhoz:\n");
		
		List<VM> vmList = dao.findAllCinemaCustomerRelationships();
		for (VM vm : vmList) {
		    if (vm != null) {
		        System.out.println("Mozi: " + vm.getMoziIDVM() + ", Vevõ: " + vm.getVevoIDVM());
		    } else {
		        System.out.println("Null VM objektum!");
		    }
		}
		
		// Minden mozi kiírása, amelynek van legalább egy kapcsolatat
		
        System.out.println("Minden mozi, amelynek legalább egy kapcsolata van:\n");
		
		List<Mozi> moziList = dao.findCinemasWithCustomers();
		for (Mozi m : moziList) {
			System.out.println("MoziID: " + m.getMoziID() + ", mozi neve: " + m.getmNev() +
					", mozi helye: " + m.getHely());
		}
		
		// Mozi törlése
		
        System.out.print("Szeretnél törölni egy mozit? (i/n): ");
        
        String delete = sc.nextLine();
        
        if(delete.contains("i")) {
        
	        System.out.print("Kérlek add meg a MoziID-t: ");
	        int id = sc.nextInt();
	
	        dao.deleteCinemaRelationshipById(id);
	        dao.deleteCinemaById(id);
	
	        System.out.println("A(z)" + id + " ID Mozi sikeresen törölve.");
	        
	        sc.close();
	        
	        // getAllCinemaData
			
	        System.out.println("Összes mozi adata:\n");
			
			cinemas = dao.getAllCinemaData();
			for(Mozi m : cinemas) {
				System.out.println("MoziID: " + m.getMoziID() + ", mozi neve: " + m.getmNev() +
						", mozi helye: " + m.getHely());
			}
        }
        
        System.out.println("----------------------------------------");

	}

}