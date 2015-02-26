	Ursache Andrei - 322CA - 29 decembrie 2014


	Aspecte generale:

	In realizarea temei am folosit scheletul de cod pus la dispozite si am 
utilizat Pattern-ul Visitor pentru evaluarea arborelui de parsare. Pa langa 
clasele din template am creat si unele noi, necesare functionarii programului.

	
	Ideea generala:

	Programul, prin metoda eval a clasei ExpressionParser, primeste un String ce
reprezinta expresia ce trebuie calculata. Mai intai se construieste arborele de
parsare corespunzator expresiei, dupa care se foloseste un vizitator specific ce
evalueaza recursiv valoarea fiecarui nod din arborele de parsare (incepand de la
nodul radacina).
	Asftel, functia eval returneaza valoarea corespunzatoare expresiei (de tip 
float), in urma aplicarii vizitatorului pe nodul radacina.


	Structura programului:
	
	Clasa "EvaluatorException" extinde "RuntimeException" si reprezinta o
exceptie ce poate sa apara in urma evaluarii unei operatii. Aceasta exceptie se
refera la cazurile in care diverse operatii nu au sens: "negative value passed 
to square root", "negative value passed to logarithm", "expression under 
logarithm evaluates to zero", "division by zero".

	Clasa "SyntacticException" extinde "Exception" si reprezinta o
exceptie ce poate sa apara la construirea arborelui de parsare. Aceasta exceptie 
se refera la cazurile in care expresia data are o forma gresita: "wrong 
paranthesis", "undefined operator", "wrong operand".

	Interfetele "Visitable" si "Visitor" sunt specifice Desing Pattern-ului 
Visitor cu ajutorul caruia se evalueaza nodurile din arborele de parsare. Ele au
forma generala care defineste tipul de interactiune intre vizitator si noduri (
metoda "accept" si respreciv "visit" ).

	Clasa "ExpressionTreeNode" implementeaza interfata Visitable si este clasa
de baza pentru fiecare nod din arbore. Ierarhia claselor din arborele de parsare
ce definesc nodurile arata astfel:
						ExpressionTreeNode <abstract>
		/								|						\
UnaryOperator <abstract>			Constant		BinaryOperator <abstract>		
	LogOperator											AdditionOperator
	SqrtOperator										SubtractionOperator
	SinOperator											MultiplicationOperator
	CosOperator											DivisionOperator
	NegativeOperator									PowerOperator
	
	Am ales sa creez o clasa specifica pentru fiecare operator unar sau binar (
log, sin, +, ^, ... ) deoarece codul este mult mai flexibil si o eventuala 
adaugare de operatori sau modificare a comportamentului actual se realizeaza 
doar prin adaugare de noi clase si metode ci nu prin modificarea celor existente
prin adaugarea de noi cazuri in switch-uri (ceea ce ar fi foarte greu de 
realizat). Cu toate ca avem destul de multe clase, Pattern-ul Visitor este 
aplicabil deoarece nu au o dinamica foarte mare.

	Clasa "BinaryOperator" extinde clasa "ExpressionTreeNode" si contine 
nodurile din stanga si dreapta (care la randul lor pot fi constante, operatori 
unari sau binari). Ea este extinsa de clasele AdditionOperator, 
SubtractionOperator, MultiplicationOperator, DivisionOperator, PowerOperator ce 
sunt specifice fiecarui tip de operator binar.

	Clasa "UnaryOperator" extinde clasa "ExpressionTreeNode" si contine 
argumentul operatorului unar (care la randul lui poate fi constant, operator 
unar sau binar). Ea este extinsa de clasele LogOperator, SqrtOperator, 
SinOperator, CosOperator, NegativeOperator ce sunt specifice fiecarui tip de 
operator unar.

	Clasa "Constant" extinde clasa "ExpressionTreeNode" si contine valoarea pe 
care o poate avea o constanta.

	In acest mod, nodurile arborelui de parsare sunt: operatori binari, 
operatori unari si constante. Este evident faptul ca pentru o expresie corect
definita frunzele arborelui sunt constante.

	Clasa "Factory" are un desing singleton si cu ajutorul ei sunt create 
instante de operatori unari si binari pe baza unui string.

	Clasa "Priority" retine prioritatile operatorilor si are o metoda statica 
"getPriority" care returneaza prioritatea unui operator.

	Clasa "TreeBuilder" este clasa cu ajutorul careia se realizeaza arborele de
parsare. Ea se foloseste de espresia data, doua stive, o instanta a clasei 
Factory si patru metode auxiliare: isNumber(), isBinaryOperator(), 
isUnaryOperator(), isParenthesis(). 
	Metoda "operate()" realizeaza prima operatie din stiva: daca primul operator 
din stiva de operatori este binar, atunci se  extrag primii doi operanzi din 
stiva de rezultate, se aplica operatorul si se introduce operandul rezultat 
inapoi in stiva de rezultate; daca primul operator din stiva de operatori este 
unar, atunci se extrage doar primul operand din stiva de rezultate si pune in 
locul sau rezultatul aplicarii operatorului pe operand.
	Metoda "buildTree()" returneaza radacina arborelui de parsare si poate arunca
exceptii de tipul SyntacticException. 
	Pentru fiecare valoare din expresia data:
	1) Daca este constanta se introduce in stiva de rezultate (se trateaza 
cazurile de exceptie);
	2) Daca este operator valid:
		2.a) Daca este paranteza "(" se introduce in stiva de operatori;
		2.b) Daca este paranteza ")" se realizeaza toate operatiile din stiva 
	de operatori pana cand se intalnseste "(" si se scoate (se trateaza 
	cazurile de exceptie);
		2.c) Daca este operatorul minus unar, atunci valoarea operatorului 
	devine "negative" pentru a-l putea diferentia de "-".
		2.d) Daca stiva de operatori este vida, atunci se introduce operatorul;
		2.e) Daca prioritatea operatorului curent este mai mare decat cea a 
	primului operator atunci se introduce operatorul curent in stiva. Aici se 
	trateaza si cazul asociativitatii operatorului "^" la dreapta in sensul in 
	care, daca ambii sunt "^" atunci se introduce;
		2.f) Daca sunt diferiti, atunci se realizeaza operatiile pana cand
	prioritatea operatorului curent devine mai mare sau stiva se goleste.
	3) Dupa ce se termina valorile din expresie, se realizeaza operatiile 
ramase (se trateaza cazurile de exceptie);
	4) Radacina arborelui devine elementul ramas in stiva de rezultate (se 
trateaza cazurile de exceptie);

	Clasa "EvaluatorVisitor" implementeaza interfata "Visitor". Ea reprezinta
vizitatorul cu ajutorul caruia se parcurge si se evalueaza arborele de parsare. 
Prin recursivitate se calculeaza valoarea fiecarui nod, in functie de 
tipul si operanzii sai. Sunt suprascrise metodele de vizitare a fiecarui tip
de nod (operator binar, unar sau constanta).
	Metoda de vizitare poate arunca exceptii de tipul "EvaluatorException" ce 
sunt tratate in momentul in care ar putea sa apara.
	

	Functionarea programului: 
	
	Metoda "eval()" a clasei "ExpressionParser" primeste ca argument expresia. 
Mai intai se creeaza un obiect de tip TreeBuilder si se obtine radacina 
arborelui de parsare (prin algoritmul prezentat mai sus). Se creeaza un 
vizitator de tipul EvaluatorVisitor si se returneaza (cu un cast la float) 
rezultatul intors de metoda accept(vizitator) a nodului radacina.
	In acest mod se obtine rezultatul expresiei in cazul in care aceasta este
corecta sau o exceptie in caz contrar.
	
	

	(!!!) 	Alte detalii referitoare la implementarea temei se regasesc in 
			fisierele sursa si, de asemenea, in fisierele javadoc din folderul
			/doc.


	Nota personala:
	
		Tema cu un enunt foarte scurt, spre deosebire de kilometrii enunturilor
	anterioare. La prima vedere nu am inteles exact cum se implementeaza,
	cu toate ca mi se parea destul de simpla ideea. Dupa studierea referintelor 
	am reusit sa imi dau seama cum se realizeaza. Termen de lucru: o zi.
	
	Andrei Ursache

