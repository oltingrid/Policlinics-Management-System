JAVA: 
> Numme proiect: JavaSample_1  (urmeaza sa-l redenumesc dar mai incolo)

> Libarii: Foloseste jdk-21  ( daca nu aveti instalat deja trebuie adaugat)

> Tooluri: WindowsBuilder for Eclipse : 
 	-este folosit pentru a face design la interfetele grafice. 
	- se instaleaza ca resursa externa: https://marketplace.eclipse.org/content/windowbuilder/help

> Deschideti proiectul JavaSample_1  in Eclipse

> Ca sa deschideti fisierele interfete alegeti fisierul java de exe AngajatiForm.java   click drepata , open with WindowsBuider Editor

> Configurarea string conectare la baza de date. Trebuie sa modificati in toate fisierle java metoda Connect () si sa scrieti credentialele voastre:
	con = DriverManager.getConnection("jdbc:mysql://localhost/policlinici", "admin","admin");

> Executia proiect / interfete : 

 Click drepata pe un MainMenu.java -> Run as java Application  / sau Debug as java application

  MainMenu e meniul principal

 Puteti sa rulati direct si fisierele separate de exemplu UtilizatoriForm.java  - > click dreapta Run as java Application

MYSQL:

> trebuie sa stergeri vechea versiune de baze de date de pe serverul vostru si sa instalati versiunea noua de dump atasata.



TODO:

> Form - Medici > de adaugat specializari_medici (un medic poate o specializare )
> Form - Medici > de adaugat competente_medici (un medic poate avea mai multe competente )


INTREBARI:
> Medic ul e pe o unitate sau pe toate unitatile ? si unde ar trebui salvata unitatea

DATE DE TEST:
> Login  / dreturi de access in functie de functia fiecarui user - vezi reguli in specificatii - fiecare functie are drept la un modul specific activitatii sale . 
login:  nume = Pop  pass= pop /  functie: inspector resurse umane / depart : resurse umane 
login:  nume = Dani  pass= dani /  functie: expert finaciar / depart : econimic  
login : nume = Rosu , pas = rosu / functie : receptioner / dept : medical  (id =9 , id_functie = 3 ) 
login : nume = Veres , pas = veres / functie : asistenta / dept : medical  (user_id =7 , id_functie= 4) 
login : nume = Chis , pas = chis / functie : medic / dept : medical  (user_id =8 , id_functie= 5) 





CONSTANTE UTILIZATE LA SETAREA DREPTURILOR:
Coduri_module : folosite in drepturile de acces din tabela drepturi_access
UtilizatoriForm / 100 
AngajatiForm / 200 
MediciForm /300 
AsistentiForm/ 400 


ProgramariForm / 500  / 1 - individual pt receptioner sa face programari si sa tipareasca bon ! 
Rapoarte Medicale / 900 /  5 - medic are fulle 
Rapoarte Abaliza / 1000  / 

FinanciarRaportProfitForm / 600
FinanciarRaportProfitMediciForm / 700 
FinanciarRaportSalariiForm / 800 

Rapoarte Medicale

Cod access: 
	// 13 -  access restrictionat/ aici inseamna ca e vb de receptioner care are doar sa salveze pacient si sa tipareasca bon 
		// 14 -  access restrictionat / aici inseamna ca e vb de asistenta care are doar sa adaauge raport analize medicale 
