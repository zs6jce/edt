/*******************************************************************************
 * Copyright © 2008, 2013 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *
 *******************************************************************************/
// NLS_ENCODING=UTF-8
// NLS_MESSAGEFORMAT_VAR
//
// Guidelines for defining Rich UI messages:  
// (1) DO NOT change the variable, "egl.eze$$rscBundles["RuiMessages"]"
// (2) The general format of a message is:
//          'MessageId'  :   'Message Text: {n}'
// (3) 'MessageId' (eg: CRRUI2007E) is composed of 4 parts:
//        - the first 5 characters is product identification, must be 'CRRUI'  
//        - the sixth character is a digit which identifies the component:
//                '0' -  Others       ( used to be 'E' )
//                '1' -  Widget       ( used to be 'I' )
//                '2' -  Runtime      ( used to be 'R' )
//                '3' -  Service      ( used to be 'S' )
//        - the 7th to 9th characters identifies message number, eg: '001'
//        - the 10th character identifies the message type:
//                'E' - error message
//                'I' - informational message
//                'W' - warning message
// (5)The message text may contain 1 or more message inserts, 
//         eg: {n},  where n is a 0 based number
// (6)Insert the message in alphabetical and numeric sort order  
//    against the MessageId to avoid conflicts which can be 
//    difficult to track at runtime            
//
egl.eze$$rscBundles["RuiMessages"] =
{
		'CRRUI0001E' : 'Nazwa InitalUI[{0}]InitialUI jest pusta. Nie mo\u017cna u\u017cy\u0107 wywo\u0142ania funkcji do zainicjowania nazwy.',		
		'CRRUI0006E' : 'Grupa RadioGroup {0} zawiera niedozwolony argument.',
					
		'CRRUI1001E' : 'Dla zmiennej {0}, {1} nie mo\u017ce mie\u0107 warto\u015bci {2}',
		'CRRUI1010E' : 'Funkcje przeci\u0105gania i upuszczania s\u0105 odniesieniami do funkcji, a nie tablicami: {0}',
		'CRRUI1020E' : 'Runtime.asDictionary: w s\u0142owniki mo\u017cna przekszta\u0142ca\u0107 tylko programy obs\u0142ugi lub rekordy, a nie {0}',
		'CRRUI1030E' : 'Nie mo\u017cna znale\u017a\u0107 definicji dla {0}.{1}',
		'CRRUI1050E' : 'Zmienna {0} musi zawiera\u0107 s\u0142owo \"null\".',
		'CRRUI1051E' : 'Nie mo\u017cna doda\u0107 widgetu do zmiennej {0}.',
		'CRRUI1055E' : 'Nie mo\u017cna doda\u0107 elementu potomnego {1} do zmiennej {2}.',		
		'CRRUI1057E' : 'Nie mo\u017cna doda\u0107 widgetu {0} do niego samego.',	
		'CRRUI1058E' : 'Nie mo\u017cna doda\u0107 przodka {1} do zmiennej {0}. Bie\u017c\u0105cym przodkiem jest {2}.',
		'CRRUI1060E' : 'Pr\u00f3ba ustawienia elementu nadrz\u0119dnego dla widgetu \"{0}\" na {1}:{2}; Elementem nadrz\u0119dnym musi by\u0107 widget',
		'CRRUI1070E' : 'Wyst\u0105pi\u0142 wyj\u0105tek {0} podczas przetwarzania funkcji zwrotnej. Zastosuj w kodzie blok try...OnException.',
		'CRRUI1071E' : 'Nie znaleziono procedury obs\u0142ugi wyj\u0105tku dla wywo\u0142ania us\u0142ugi. Dodaj procedur\u0119 obs\u0142ugi wyj\u0105tku dla wywo\u0142ania us\u0142ugi.',
		'CRRUI1072E' : 'Wyst\u0105pi\u0142 wyj\u0105tek {0} w funkcji zwrotnej b\u0142\u0119du, zastosuj w kodzie blok try...OnException',		
		'CRRUI1080E' : 'Widget nie ma elementu DOM i nie znajduje si\u0119 w dokumencie.<br>Bie\u017c\u0105ce atrybuty tego widgetu to:<P>{0}',
		'CRRUI1083E' : 'Wyst\u0105pi\u0142 b\u0142\u0105d podczas obs\u0142ugi zdarzenia przegl\u0105darki {0}.',
		'CRRUI1150E' : 'Pr\u00f3ba usuni\u0119cia warto\u015bci \"null\" ze zmiennej {0} nie powiod\u0142a si\u0119.',		
		'CRRUI1151E' : 'Pr\u00f3ba usuni\u0119cia widgetu bez elementu DOM ze zmiennej {0} nie powiod\u0142a si\u0119.',		
		'CRRUI1155E' : 'Nie mo\u017cna usun\u0105\u0107 elementu potomnego typu {1} ze zmiennej {0}: {2}.',
		'CRRUI1157E' : 'Nie mo\u017cna usun\u0105\u0107 widgetu z niego samego. Typ widgetu: {0}.',
			
		'CRRUI2002E' : '{1}<br>Wyst\u0105pi\u0142 b\u0142\u0105d w {0}: {2}',
		'CRRUI2004E' : 'Historia {0} zawiera niedozwolony argument.',
		'CRRUI2005E' : 'Nie mo\u017cna u\u017cy\u0107 odniesienia o warto\u015bci NULL.',
		'CRRUI2006E' : '{0}', // The text of this message comes from some other error message.
		'CRRUI2007E' : 'Niedozwolony argument: {0}.',
		'CRRUI2009E' : 'Nie mo\u017cna utworzy\u0107 instancji zdarzenia z procedury obs\u0142ugi RUI.',
		'CRRUI2010E' : 'Nieaktualna funkcja: {0}.',
		
		'CRRUI2015E' : 'Brak pliku {0} dla biblioteki RUIPropertiesLibrary {1}',
		'CRRUI2016E' : 'Program EGL zu\u017cywa za du\u017co czasu',
		'CRRUI2017E' : 'Nie mo\u017cna przekszta\u0142ci\u0107 warto\u015bci \"{0}\" typu {1} w typ {2}',
		'CRRUI2018E' : 'Wyst\u0105pi\u0142o przepe\u0142nienie podczas przypisywania warto\u015bci {0} do typu {1}',
		'CRRUI2019E' : 'Nie mo\u017cna do\u0142\u0105czy\u0107 elementu {0} do tablicy. Maksymalna wielko\u015b\u0107 tablicy to {1}',
		'CRRUI2020E' : 'Niepoprawny argument {0} dla funkcji tablicowej setMaxSize()',
		'CRRUI2021E' : 'Nie mo\u017cna uzyska\u0107 nast\u0119pnego tokenu z \u0142a\u0144cucha tekstowego zaczynaj\u0105cego si\u0119 od indeksu {0}',
		'CRRUI2022E' : 'Indeks {0} jest poza zakresem dla tej tablicy. Wielko\u015b\u0107 tablicy wynosi {1}',
		'CRRUI2023E' : 'Zosta\u0142o u\u017cyte odniesienie o warto\u015bci NULL: {0}',
		'CRRUI2024E' : 'Dost\u0119p dynamiczny do klucza \"{0}\" nie jest poprawny na obiekcie typu {1}',
		'CRRUI2025E' : 'Dost\u0119p dynamiczny nie powi\u00f3d\u0142 si\u0119, nie ma takiego klucza: \"{0}\"',
		'CRRUI2030E' : 'Do funkcji XMLLib.convertFromXML przekazano niepoprawny argument. Oczekiwany by\u0142 \u0142a\u0144cuch. Otrzymano warto\u015b\u0107 \"{0}\"',
		'CRRUI2031E' : 'Wyst\u0105pi\u0142 b\u0142\u0105d podczas analizowania pliku XML: {0}',
		'CRRUI2032E' : 'Wzorzec datownika {0} jest niepoprawny',
		'CRRUI2033E' : 'Warto\u015b\u0107 {0} okre\u015blona dla funkcji tablicowej resize() jest wi\u0119ksza ni\u017c maksymalna wielko\u015b\u0107 tablicy {1}',
		'CRRUI2034E' : 'Typ {0} jest niepoprawny dla elementu tablicy',
		'CRRUI2035E' : 'Liczba wymiar\u00f3w po powi\u0119kszeniu przekracza liczb\u0119 wymiar\u00f3w tablicy',
		'CRRUI2036E' : 'B\u0142\u0105d domeny w wywo\u0142aniu do {0}: argument musi by\u0107 z zakresu od {1} do {2}',
		'CRRUI2037E' : 'B\u0142\u0105d obliczeniowy: nie mo\u017cna dzieli\u0107 liczby przez 0',
		'CRRUI2038E' : 'B\u0142\u0105d domeny w wywo\u0142aniu do {0}: argument musi by\u0107 wi\u0119kszy od zera',
		'CRRUI2039E' : 'B\u0142\u0105d domeny w wywo\u0142aniu do {0}: argument musi by\u0107 wi\u0119kszy lub r\u00f3wny zero',
		'CRRUI2040E' : 'B\u0142\u0105d domeny w wywo\u0142aniu do {0}: wyk\u0142adnik przy podstawie zero musi by\u0107 wi\u0119kszy od zera',
		'CRRUI2041E' : 'B\u0142\u0105d domeny w wywo\u0142aniu do {0}: wyk\u0142adnik przy ujemnej podstawie musi by\u0107 liczb\u0105 ca\u0142kowit\u0105',
		'CRRUI2042E' : 'Niepoprawne indeksy pod\u0142a\u0144cucha {0}:{1}. ',
		'CRRUI2050E' : 'B\u0142\u0105d obliczeniowy: abs() - podana liczba argument\u00f3w: {0}. Oczekiwano 0 lub 1',
		'CRRUI2051E' : 'B\u0142\u0105d obliczeniowy: add() - podana liczba argument\u00f3w: {0}. Oczekiwano 1 lub 2',
		'CRRUI2052E' : 'B\u0142\u0105d obliczeniowy: compareTo() - podana liczba argument\u00f3w: {0}. Oczekiwano 1 lub 2',
		'CRRUI2053E' : 'B\u0142\u0105d obliczeniowy: divide() - ujemna skala, {0}, jest niepoprawna',
		'CRRUI2054E' : 'B\u0142\u0105d obliczeniowy: divide() - podana liczba argument\u00f3w: {0}. Oczekiwano od 1 do 3',
		'CRRUI2055E' : 'B\u0142\u0105d obliczeniowy: divideInteger() - podana liczba argument\u00f3w: {0}. Oczekiwano 1 lub 2',
		'CRRUI2056E' : 'B\u0142\u0105d obliczeniowy: max() - podana liczba argument\u00f3w: {0}. Oczekiwano 1 lub 2',
		'CRRUI2057E' : 'B\u0142\u0105d obliczeniowy: min() - podana liczba argument\u00f3w: {0}. Oczekiwano 1 lub 2',
		'CRRUI2058E' : 'B\u0142\u0105d obliczeniowy: multiply() - podana liczba argument\u00f3w: {0}. Oczekiwano 1 lub 2',
		'CRRUI2059E' : 'B\u0142\u0105d obliczeniowy: negate() - podana liczba argument\u00f3w: {0}. Oczekiwano 0 lub 1',
		'CRRUI2060E' : 'B\u0142\u0105d obliczeniowy: plus() - podana liczba argument\u00f3w: {0}. Oczekiwano 0 lub 1',
		'CRRUI2061E' : 'B\u0142\u0105d obliczeniowy: pow() - podana liczba argument\u00f3w {0}. Oczekiwano 1 lub 2',
		'CRRUI2062E' : 'B\u0142\u0105d obliczeniowy: pow() - ujemna pot\u0119ga, {0}',
		'CRRUI2063E' : 'B\u0142\u0105d obliczeniowy: pow() - za du\u017co cyfr, {0}',
		'CRRUI2064E' : 'B\u0142\u0105d obliczeniowy: remainder() - podana liczba argument\u00f3w: {0}. Oczekiwano 1 lub 2',
		'CRRUI2065E' : 'B\u0142\u0105d obliczeniowy: subtract() - podana liczba argument\u00f3w: {0}. Oczekiwano 1 lub 2',
		'CRRUI2066E' : 'B\u0142\u0105d obliczeniowy: format() - podana liczba argument\u00f3w: {0}. Oczekiwano 2 lub 6',
		'CRRUI2067E' : 'B\u0142\u0105d obliczeniowy: format() - przepe\u0142nienie wyk\u0142adnika, {0}',
		'CRRUI2068E' : 'B\u0142\u0105d obliczeniowy: intValueExact() - niezerowa cz\u0119\u015b\u0107 dziesi\u0119tna, {0}',
		'CRRUI2069E' : 'B\u0142\u0105d obliczeniowy: intValueExact() - przepe\u0142nienie konwersji, {0}',
		'CRRUI2070E' : 'B\u0142\u0105d obliczeniowy: setScale() - podana liczba argument\u00f3w: {0}. Oczekiwano 1 lub 2',
		'CRRUI2071E' : 'B\u0142\u0105d obliczeniowy: setScale() - ujemna skala, {0}',
		'CRRUI2072E' : 'B\u0142\u0105d obliczeniowy: intCheck() - b\u0142\u0105d konwersji, {0}',
		'CRRUI2073E' : 'B\u0142\u0105d obliczeniowy: dodivide() - przepe\u0142nienie liczby ca\u0142kowitej',
		'CRRUI2074E' : 'B\u0142\u0105d obliczeniowy: Nie mo\u017cna przekszta\u0142ci\u0107 \u0142a\u0144cucha \"{1}\" w liczb\u0119',
		'CRRUI2075E' : 'B\u0142\u0105d obliczeniowy: Argument o numerze {0} dla metody {1} jest niepoprawny. Podano argument {2}',
		'CRRUI2076E' : 'B\u0142\u0105d obliczeniowy: Za du\u017co cyfr - {0}',
		'CRRUI2077E' : 'B\u0142\u0105d obliczeniowy: round() - podana liczba argument\u00f3w: {0}. Oczekiwano 1 lub 2',
		'CRRUI2078E' : 'B\u0142\u0105d obliczeniowy: round() - zaokr\u0105glenie niezb\u0119dne',
		'CRRUI2079E' : 'B\u0142\u0105d obliczeniowy: round() - z\u0142a warto\u015b\u0107 zaokr\u0105glenia, {0}',
		'CRRUI2080E' : 'B\u0142\u0105d obliczeniowy: round() - przepe\u0142nienia wyk\u0142adnika, {0}',
		'CRRUI2081E' : 'B\u0142\u0105d obliczeniowy: finish() - przepe\u0142nienia wyk\u0142adnika, {0}',
		'CRRUI2082E' : 'B\u0142\u0105d wewn\u0119trzny: podczas wywo\u0142ywania konstruktora dla {0}',
		'CRRUI2083E' : 'B\u0142\u0105d wewn\u0119trzny: wyst\u0105pi\u0142 problem podczas definiowania klasy {0}',
		'CRRUI2084E' : 'B\u0142\u0105d wewn\u0119trzny: podczas definiowania widgetu {0}.{1} jako podklasy klasy egl.ui.rui.RUIPropertiesLibrary',
		'CRRUI2085E' : 'B\u0142\u0105d wewn\u0119trzny: podczas definiowania widgetu {0}.{1} jako podklasy klasy {2}.{3}',
		'CRRUI2086E' : 'B\u0142\u0105d wewn\u0119trzny: wyst\u0105pi\u0142 problem podczas definiowania programu obs\u0142ugi RUIHandler {0}',
		'CRRUI2087E' : 'B\u0142\u0105d wewn\u0119trzny: wyst\u0105pi\u0142 problem podczas definiowania widgetu RUI {0}',
		'CRRUI2088E' : 'Ta przegl\u0105darka nie jest obs\u0142ugiwana przez interfejs EGL Rich UI',
		'CRRUI2089E' : 'Nie mo\u017cna wykona\u0107 konwersji z formatu JSON: "{0}", przyczyna: {1}',
		'CRRUI2090E' : 'Nie mo\u017cna wywo\u0142a\u0107 us\u0142ugi: {0}',
		'CRRUI2091E' : 'Nie mo\u017cna wys\u0142a\u0107 zdarzenia do \u015brodowiska IDE Eclipse: {0}',
		'CRRUI2092E' : 'B\u0142\u0105d wewn\u0119trzny: wyst\u0105pi\u0142 problem podczas obs\u0142ugi zdarzenia IDE {0}',
		'CRRUI2093E' : 'B\u0142\u0105d wewn\u0119trzny: nie mo\u017cna instrumentowa\u0107 funkcji {0}',
		'CRRUI2094E' : 'Wywo\u0142ania funkcji EGL prowadz\u0105ce do tego b\u0142\u0119du:',
		'CRRUI2095E' : 'Nie mo\u017cna odszuka\u0107 wywo\u0142a\u0144 funkcji EGL prowadz\u0105cych do tego b\u0142\u0119du',
		'CRRUI2097E' : 'Niepoprawna warto\u015b\u0107 stylu CSS "{1}" dla atrybutu {0}',
		'CRRUI2097E' : 'Nie mo\u017cna poprawnie przeanalizowa\u0107 stylu CSS {0}. Sprawd\u017a sk\u0142adni\u0119 lub u\u017cyj zewn\u0119trznego arkusza styl\u00f3w.',
		'CRRUI2098E' : 'Wyst\u0105pi\u0142 b\u0142\u0105d podczas obs\u0142ugi metody przeci\u0105gania i upuszczania: {0}',
		'CRRUI2099E' : 'W technologii Rich UI operacja kodu EGL "set" nie jest obs\u0142ugiwana dla typu {0}',
		'CRRUI2100E' : 'Niepoprawny argument metody RuiLib.convertFromXML. Oczekiwano \u0142a\u0144cucha. Otrzymano obiekt typu {0}',
		'CRRUI2101E' : 'Nie mo\u017cna znale\u017a\u0107 instrukcji indexOf "{1}" w "{0}" z powodu {2}',
		'CRRUI2102E' : 'Nie mo\u017cna sortowa\u0107 tablicy',
		'CRRUI2103E' : 'Niedozwolony dost\u0119p do "{0}" w obiekcie typu "{1}" z powodu {2}',
		'CRRUI2104E' : 'Nie mo\u017cna przeanalizowa\u0107 \u0142a\u0144cucha JSON "{0}"',
		'CRRUI2105E' : 'Wyst\u0105pi\u0142 b\u0142\u0105d analizy JSON podczas pr\u00f3by ustawienia {0}. Pole nie istnieje w oczekiwanym miejscu w \u0142a\u0144cuchu JSON.',
		'CRRUI2106E' : 'Pr\u00f3ba ustawienia warto\u015bci null dla {0} nie powiod\u0142a si\u0119 podczas analizy JSON, poniewa\u017c pole to nie zosta\u0142o zadeklarowane jako pole, kt\u00f3re mo\u017ce mie\u0107 warto\u015b\u0107 null.',
		'CRRUI2107E' : 'Funkcje konwersji JSON biblioteki us\u0142ug dzia\u0142aj\u0105 na rekordzie lub s\u0142owniku. Element {0} nie jest obs\u0142ugiwanym typem.',
		'CRRUI2108E' : 'Funkcje konwersji XML biblioteki XML dzia\u0142aj\u0105 na rekordzie. Element {0} nie jest obs\u0142ugiwanym typem.',
		
		'CRRUI2111E' : 'B\u0142\u0105d MathContext(): Podana liczba argument\u00f3w ({0}) jest niepoprawna. Oczekiwano od 1 do 4.',
		'CRRUI2112E' : 'B\u0142\u0105d MathContext(): Podana liczba cyfr ({0}) jest zbyt ma\u0142a.',
		'CRRUI2113E' : 'B\u0142\u0105d MathContext(): Podana liczba cyfr ({0}) jest zbyt du\u017ca.',
		'CRRUI2114E' : 'B\u0142\u0105d MathContext(): Podano niepoprawn\u0105 warto\u015b\u0107 formularza ({0}).',
		'CRRUI2115E' : 'B\u0142\u0105d MathContext(): Podano niepoprawn\u0105 warto\u015b\u0107 ({0}) trybu zaokr\u0105glania.',
		
	    'CRRUI2700E' : 'Nie odebrano danych wej\u015bciowych dla wymaganego pola - nale\u017cy je wprowadzi\u0107 ponownie.',
		'CRRUI2702E' : 'W danych wej\u015bciowych wyst\u0105pi\u0142 b\u0142\u0105d typu danych - dane nale\u017cy wprowadzi\u0107 ponownie.',
		'CRRUI2703E' : 'Przekroczono dozwolon\u0105 maksymaln\u0105 liczb\u0119 cyfr znacz\u0105cych - nale\u017cy ponownie wprowadzi\u0107 warto\u015b\u0107.',
		'CRRUI2704E' : 'Dane wej\u015bciowe nie nale\u017c\u0105 do zdefiniowanego zakresu od {0} do {1} - nale\u017cy je wprowadzi\u0107 ponownie.',
		'CRRUI2705E' : 'B\u0142\u0105d dotycz\u0105cy minimalnej d\u0142ugo\u015bci wprowadzonych danych - nale\u017cy ponownie wprowadzi\u0107 dane.',
		'CRRUI2707E' : 'W danych wej\u015bciowych wyst\u0105pi\u0142 b\u0142\u0105d sprawdzania modulo - nale\u017cy ponownie wprowadzi\u0107 dane.',
		'CRRUI2708E' : 'Niepoprawne dane wej\u015bciowe dla zdefiniowanego formatu daty lub godziny {0}.',
		'CRRUI2710E' : 'Dane wej\u015bciowe s\u0105 niepoprawne dla pola danych typu boolowskiego.',
		'CRRUI2712E' : 'Dane szesnastkowe s\u0105 niepoprawne.',
		'CRRUI2713E' : 'Wprowadzona warto\u015b\u0107 jest niepoprawna, poniewa\u017c jest niezgodna z ustawionym wzorcem.',		
		'CRRUI2716E' : 'Dane wej\u015bciowe nie nale\u017c\u0105 do zdefiniowanej listy poprawnych warto\u015bci - nale\u017cy je wprowadzi\u0107 ponownie.',
		'CRRUI2717E' : 'Okre\u015blony format daty i godziny elementu {0} jest niepoprawny.',	
		'CRRUI2719E' : 'B\u0142\u0105d podczas analizowania warto\u015bci wej\u015bciowej.',

		'CRRUI3650E' : 'Nie mo\u017cna znale\u017a\u0107 pliku deskryptora wdra\u017cania: {0}',
		'CRRUI3651E' : 'Klucz powi\u0105zania us\u0142ugi: \'{0}\' nie istnieje w deskryptorze wdra\u017cania \'{1}\'',
		'CRRUI3652E' : 'B\u0142\u0119dny typ powi\u0105zania us\u0142ugi \'{0}\', oczekiwano typu powi\u0105zania \'{1}\'',
		'CRRUI3653E' : 'Wyst\u0105pi\u0142 b\u0142\u0105d podczas pr\u00f3by wywo\u0142ania us\u0142ugi REST w \'{0}\'',
		'CRRUI3654E' : 'Wyst\u0105pi\u0142 b\u0142\u0105d podczas budowania obiektu \u017c\u0105dania: \'{0}\'',
		'CRRUI3655E' : 'Wyst\u0105pi\u0142 b\u0142\u0105d podczas przetwarzania obiektu \u017c\u0105dania: \'{0}\'',
		'CRRUI3656E' : 'Format \'formdata\' nie jest obs\u0142ugiwany jako format odpowiedzi',
		'CRRUI3657E' : 'Pod adresem {0} nie mo\u017cna znale\u017a\u0107 serwera proxy dotworzenia wywo\u0142a\u0144 us\u0142ugi',
		'CRRUI3658E' : 'Wyst\u0105pi\u0142 b\u0142\u0105d w proxy pod adresem \'{0}\' podczas pr\u00f3by wywo\u0142ania us\u0142ugi w \'{1}\'',
		'CRRUI3659E' : 'Analiza odpowiedzi JSON da\u0142a w wyniku warto\u015b\u0107 NULL, oryginalna odpowied\u017a: \'{0}\'',
		'CRRUI3660E' : 'Wyst\u0105pi\u0142 wyj\u0105tek, nie mo\u017cna obs\u0142u\u017cy\u0107 odpowiedzi dla \'{0}\', przyczyna: \'{1}\'',
		'CRRUI3661E' : 'Brak informacji o powi\u0105zaniu us\u0142ugi dla \'{0}\' podczas pr\u00f3by wywo\u0142ania funkcji us\u0142ugi \'{1}\'.'
};

