	Ursache Andrei - 2 decembrie 2014
	
	----------------------------------------------------------------------------
	Proiect anul 2, primul semestru, la materia Programarea Orientata pe 
	Obiecte.
	Subiect: construirea unui editor de poze ce poate indeplini aplica diverse
	filtre / efecte in functie de centrul de prelucrare.
	Limbaj ales: Java
	
	############################     ATENTIE !!!    ############################
	Temele sunt verificate pe VMChecker. Nu incercati sa copiati codul, deoarece
	VMChecker verifica si sursele existente pe GitHub. In plus, fiecare tema va 
	ajuta foarte mult daca incercati sa o realizati singuri. Asa ca, daca ai
	ajuns aici pentru a cauta o tema, iti recomand calduros sa inchizi tab-ul cu 
	GitHub si sa te apuci singur de ea. Spor!
	----------------------------------------------------------------------------


	Aspecte generale:

	In realizarea temei am folosit scheletul de cod pus la dispozite. Pa langa 
clasele din template am creat si unele noi, necesare functionarii programului.

	
		Ideea generala:

	Programul primeste ca argument in linie de comanda numele fisierului de 
configurare pe baza caruia se creeaza centrele de mesaje si se asociaza 
fiecaruia componentele specifice si vecinii. Apoi, programul primeste de la 
stdin, pe rand, informatii despre imaginea care trebuie capturata si cauta un 
centru adecvat pentru fiecare operatie ce trebuie realizata, pana la primirea
comenzii "exit". Informatiile despre imagine sunt in formatul urmator:
"imageIN imageOUT pre(flash=value;*zoom=x,y,x,y) photo(type=value) 
post(*operation1, operation2,...)", unde campurile marcate cu "*" pot sa 
lipseasca.
	Astfel, programul scrie pe disk pozele obtinute in urma modificarilor 
cerute de catre utilizator.


		Structura programului:

	Programul este impartit in trei pachete (+ cel default), iar ierarhia de 
clase poate fi vizualizata astfel:

	Pachetul "components": contine toate clasele specifice componenteleor ce au 
rolul de a aduce o modificare imaginii. Toate au la baza clasa de baza 
"Components" care contine informatii despre tipul taskului ce trebuie realizat, 
precum si o metoda "notify" care realizeaza acest task. Componentele pot fi 
impartite in 3 categorii. 
	Prima categorie poate fi cea a componentelor care asigura citirea si 
scrierea pe disk a imaginii: ImageLoader si ImageSaver, implementate in 
scheletul de cod.
	A doua categorie de componente este cea a modificarilor ce pot fi aduse 
imaginii inainte de capturare: Flash si Zoom. Clasa Flash are o metoda care 
calculeaza luminozitatea medie cu ajutorul careia se decide daca un flash auto
aduce sau nu modificari asupra imaginii. Clasa Zoom prin metoda notify creeaza
o noua matrice de pixeli corespunzatoare celor doua puncte primite ca parametru.
	A treia categorie de componente corespunde modificarilor ulterioare 
capturarii imaginii. In primul rand, indiferent de orientarea pe care dorim sa
o aiba fotografia (raw/normal), imediat dupa capturare se obtine o imagine 
rasturnata. Astfel, clasa RawPhoto are o metoda statica care aduce aceasta 
modificare unei poze, deoarece codul poate fi foarte usor refolosit in clasa
NormalPhoto. Pe langa aceste clase, se mai gasesc si Sepia, Blur si BlackWhite, 
care aduc imaginii modificarile cu acelasi nume. Mentionez ca de fiecare data 
cand a fost posibil nu am folosit o matrice auxiliara si nu am alocat un nou 
mesaj pentru a-l returna, ci l-am modificat pe cel primit, entru a nu folosi
foarte multa memorie auxiliara, avand in vedere faptul ca se lucreaza cu matrice
destul mari cantitativ.

	Pachetul "messaging": contine clasele specifice procesarii mesajelor. De 
asemenea, aici se afla si clasa de baza, abstracta, a centrelor de mesaje, 
MessageCenter, care este extinsa de clasa Center. Am ales acest mod de 
implementare (si nu clasa anonima), deoarece mi s-a parut mai "natural". Putem 
in acest mod sa definim mai multe tipuri de centre de mesaje, poate ne vom baza 
pe continentul de pe care face parte centrul de mesaje sau vom implementa
alte tipuri de conexiuni intre ele. 
	Acest pachet contine si clasele specifice mesajelor care se transmit (sau 
se primesc pentru fiecare task in parte): MessageLoad, MessageSave, MessageImage,
MessageSuccess, MessageZoom, MessageFlash.
	Am creat aici si o clasa, IDGenerator, cu ajutorul careia asigur atribuirea
unui ID unic fiecarui mesaj.
 
	Pachetul "types" contine doua enumeratii care definesc tipurile de flash si 
task-uri: TaskType si FlashType. 

	In pachetul "default" (dupa cum este in scheletul de cod oferit) se afla 
clasa SimulationMeneger, motorul programului, care, cu ajutorul claselor Factory
(creearea tipurilor de flash si zoom pe baza de string-uri) si Values (clasa care
parseaza linia citita de la stdin si retine valorile fiecarui parametru), 
implementeaza modul de interactiune cu utilizatorul. Am considerat ca este 
necesara crearea unei clase care sa retina valorile citite de la stdin pentru a 
a avea o viziune mai structurata si mai flexibila a datelor si sa pot accesa
mai intuidiv datele.


		Implementarea programului: 
	
	Programul primeste ca parametru in linie de comanda numele fisierului in 
care se gaseste configurarea centrelor de mesaje. Astfel, SimulationManager 
prelucreaza aceste informatii si creeaza reteaua de centre de mesaje cu ajutorul 
functiei "buildNetwork". Apoi, se citeste de la stdin cate o linie pana la 
intalnirea mesajului "exit". Pentru fiecare linie in parte se retin valorile
parametrilor imaginii cu ajutorul clasei Values. Fiecare mesaj este procesat 
apeland metoda publish() in centrul de mesaje "principal". La randul sau, aceasta
metoda va apela algoritmul de cautare a centrului potrivit pentru executarea 
task-ului si va returna mesajul ce contine informatiile modificate. Indiferent 
de imagine, va fi creat un MessageLoad care va returna MessageImage-ul pe care
il vom folosi in majoritatea operatiilor viitoare. Apoi, se creeaza un mesaj de 
tip flash cu tipul specificat si un mesaj de tip zoom (daca este cazul). Dupa 
aceasta se va publica un mesaj pentru raw, iar daca se specifica faptul ca 
imaginea trebuie sa fie in format "normal", se va creea si un mesaj cu task-ul 
specificat NormalPhoto (care reutilizeaza codul de la RawPhoto).
	Apoi, pentu fiecare post-operatie se va creea un mesaj specific si se va 
publica (efectua), iar in final se va creea un mesaj de tip MessageSave si prin 
publicarea acestuia se va salva imaginea pe disk.
	La primirea comenzii "exit", se va iesi din program.
	
	

	(!!!) 	Alte detalii referitoare la implementarea temei se regasesc in 
			fisierele sursa si, de asemenea, in fisierele javadoc din folderul
			/doc.
		
	
	Andrei Ursache

