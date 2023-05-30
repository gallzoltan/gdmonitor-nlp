package hu.gallz.appservice.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import hu.gallz.appservice.model.Decree;

public class DecreeIteratorTest {
	
	@Test
	public void givenDecreeList_whenDecreeIteratorUsed_thenOnlyRelevantDecree() {		
		List<Decree> decrees = getDecrees();
		
		DecreeIterator decreeIterator = new DecreeIterator(decrees);
		int count = 0;
		while (decreeIterator.hasNext()) {
			decreeIterator.next();
	        count++;
	    }
	    assertEquals(6, decrees.size());
	    assertEquals(1, count);
	}
	
	private List<Decree> getDecrees() {
		List<Decree> decrees = new ArrayList<>();
		
		Decree decree1 = new Decree(18, 
				"A Kormány 1180/2023. (V. 8.) Korm. határozata", 
				"A Kormány 1180/2023. (V. 8.) Korm. határozata\r\n"
				+ "az online árfigyelő adatbázis létrehozásáról és működtetéséről\r\n"
				+ "A Kormány\r\n"
				+ " 1. egyetért azzal, hogy a  gazdasági hatékonyságot és a  társadalmi felemelkedést szolgáló piaci verseny ösztönzése, \r\n"
				+ "az  infláció letörése, a  fogyasztók védelme és tájékoztatása érdekében kerüljön létrehozásra a  Gazdasági \r\n"
				+ "Versenyhivatal által működtetendő online árfigyelő rendszer, amely a  fogyasztóknak megvételre kínált egyes \r\n"
				+ "termékek napi áráról és törzsvásárlói áráról kínál nyilvános információt;\r\n"
				+ " 2. felhívja a  gazdaságfejlesztési minisztert – az  agrárminiszter egyetértésével – olyan miniszteri rendelet \r\n"
				+ "megalkotására, amely az  1.  pont szerinti árfigyelő rendszerbe tartozó termékkategóriák felsorolását, az  egyes \r\n"
				+ "termékkategóriákba tartozó, bejelentési kötelezettséggel érintett termékek körét, valamint a  termékekhez tartozó \r\n"
				+ "ártípusokat tartalmazza.\r\n"
				+ "Felelős: gazdaságfejlesztési miniszter \r\n"
				+ "agrárminiszter\r\n"
				+ "Határidő: 2023. június 15.\r\n"
				+ "  Orbán Viktor s. k.,");		
		decrees.add(decree1);
		
		Decree decree2 = new Decree(18, 
				"A Kormány 1181/2023. (V. 8.) Korm. határozata", 
				"A Kormány 1181/2023. (V. 8.) Korm. határozata\r\n"
				+ "fejezetek közötti előirányzat-átcsoportosításról\r\n"
				+ "A Kormány az államháztartásról szóló 2011. évi CXCV. törvény 33. § (2) bekezdésében biztosított jogkörében eljárva \r\n"
				+ "210 000 000 forint egyszeri átcsoportosítását rendeli el a  Magyarország 2023.  évi központi költségvetéséről szóló \r\n"
				+ "2022.  évi XXV.  törvény (a  továbbiakban: Kvtv.) 1.  melléklet XLIII. Az  állami vagyonnal kapcsolatos bevételek és \r\n"
				+ "kiadások fejezet, 2. A GFM tulajdonosi joggyakorlásával kapcsolatos bevételek és kiadások cím, 2. A GFM tulajdonosi \r\n"
				+ "joggyakorlásával kapcsolatos kiadások alcím, 1. A  GFM tulajdonosi joggyakorlása alá tartozó társaságok \r\n"
				+ "forrásjuttatásai jogcímcsoport terhére, a  Kvtv. 1.  melléklet XXI. Miniszterelnöki Kabinetiroda fejezet, 20.  Fejezeti \r\n"
				+ "kezelésű előirányzatok cím, 1. Célelőirányzatok alcím, 28. Kormányzati infokommunikációs szolgáltatások \r\n"
				+ "jogcímcsoport javára, az 1. melléklet szerint.\r\n"
				+ "Felelős: pénzügyminiszter \r\n"
				+ "gazdaságfejlesztési miniszter \r\n"
				+ "Miniszterelnöki Kabinetirodát vezető miniszter\r\n"
				+ "Határidő: azonnal\r\n"
				+ "  Orbán Viktor s. k.,");
		decrees.add(decree2);
		
		Decree decree3 = new Decree(20, 
				"A Kormány 1182/2023. (V. 8.) Korm. határozata", 
				"A Kormány 1182/2023. (V. 8.) Korm. határozata"
				+ "a Központi Maradványelszámolási Alap előirányzatból, a Rezsivédelmi Alap központi kiadása előirányzatból \r\n"
				+ "történő, valamint fejezeten belüli és fejezetek közötti előirányzat-átcsoportosításról\r\n"
				+ "A Kormány\r\n"
				+ " 1. az államháztartásról szóló 2011.  évi CXCV.  törvény (a  továbbiakban: Áht.) 33.  § (2)  bekezdésében biztosított \r\n"
				+ "jogkörében eljárva 32 563 040 380 forint egyszeri átcsoportosítását rendeli el, az 1. melléklet szerint;\r\n"
				+ "Felelős: pénzügyminiszter\r\n"
				+ "Határidő: azonnal\r\n"
				+ " 2. az Áht. 33. § (2) bekezdésében, valamint az államháztartásról szóló törvény végrehajtásáról szóló 368/2011. (XII. 31.) \r\n"
				+ "Korm. rendelet 153. §-ában biztosított jogkörében eljárva 48 245 193 161 forint egyszeri átcsoportosítását rendeli el \r\n"
				+ "a Magyarország 2023. évi központi költségvetéséről szóló 2022. évi XXV. törvény (a továbbiakban: Kvtv.) 1. melléklet \r\n"
				+ "XLII.  A  költségvetés közvetlen bevételei és kiadásai fejezet, 45. Központi Maradványelszámolási Alap cím terhére, \r\n"
				+ "a 2. melléklet szerint;\r\n"
				+ "Felelős: pénzügyminiszter\r\n"
				+ "Határidő: azonnal\r\n"
				+ " 3. az Áht. 33.  § (2)  bekezdésében biztosított jogkörében eljárva 9 656 933 044 forint egyszeri átcsoportosítását \r\n"
				+ "rendeli el a Kvtv. 1. melléklet L. Rezsivédelmi Alap fejezet terhére, a 3. melléklet szerint.\r\n"
				+ "Felelős: pénzügyminiszter\r\n"
				+ "Határidő: azonnal\r\n"
				+ "  Orbán Viktor s. k.,");
		decrees.add(decree3);
		
		Decree decree4 = new Decree(27, 
				"A Kormány 1183/2023. (V. 8.) Korm. határozata", 
				"A Kormány 1183/2023. (V. 8.) Korm. határozata\r\n"
				+ "az önkormányzatok adósságot keletkeztető, valamint kezesség-, illetve garanciavállalásra vonatkozó \r\n"
				+ "ügyleteihez történő 2023. márciusi előzetes kormányzati hozzájárulásról\r\n"
				+ "A Kormány a  Magyarország gazdasági stabilitásáról szóló 2011. évi CXCIV. törvény 10.  § (1)  bekezdése alapján, az  adósságot \r\n"
				+ "keletkeztető ügyletekhez történő hozzájárulás részletes szabályairól szóló 353/2011. (XII. 30.) Korm. rendeletben foglaltakra és \r\n"
				+ "a  veszélyhelyzet idején az  önkormányzatok és gazdasági társaságaik energiahatékonyság javítását szolgáló fejlesztésekhez \r\n"
				+ "történő adósságot keletkeztető ügyleteinek eltérő szabályairól szóló 442/2022. (XI. 7.) Korm. rendeletben foglaltakra tekintettel \r\n"
				+ "a következő határozatot hozza:\r\n"
				+ "A Kormány az  ügyletről az  1.  mellékletben foglaltak szerint dönt, az  engedélyezett ügylet 2023. évben történő \r\n"
				+ "megkötéséhez az a)–c) pontban foglalt kiegészítő feltételekkel járul hozzá:\r\n"
				+ "a) az önkormányzat az 1. melléklet szerint engedélyezett ügyletérték teljes összegétől lefelé eltérhet, ha ez nem \r\n"
				+ "okozza ezen érték valamelyikének növekedését egyik évben sem;\r\n"
				+ "b) az önkormányzat egy adott év végéig legfeljebb akkora összegű adósságot keletkeztethet az ügyletből, mint \r\n"
				+ "amekkora az 1. melléklet szerint az ügyletből az adott év végéig keletkező kumulált adósságösszeg;\r\n"
				+ "c) fejlesztési célú ügylet esetében az ügyletet csak az 1. mellékletben szereplő fejlesztésre lehet fordítani.\r\n"
				+ "  Orbán Viktor s. k.,");
		decrees.add(decree4);
		Decree decree5 = new Decree(29, 
				"A Kormány 1184/2023. (V. 8.) Korm. határozata", 
				"A Kormány 1184/2023. (V. 8.) Korm. határozata\r\n"
				+ "az építőipari ellátásbiztonsághoz szükséges intézkedésekről\r\n"
				+ "A Kormány felhívja az  építési és közlekedési minisztert, hogy a  gazdaságfejlesztési miniszter bevonásával \r\n"
				+ "vizsgálja  meg az  építőipari ellátásbiztonság szempontjából stratégiai jelentőségű nyersanyagok és termékek \r\n"
				+ "vonatkozásában fennálló állami elővásárlási és vételi jog gyakorlásához (a  továbbiakban: állami joggyakorlás) \r\n"
				+ "szükséges jogi, műszaki és gazdasági feltételeket, valamint a  vizsgálat eredményével összhangban készítsen \r\n"
				+ "előterjesztést a  Kormány részére az  állami joggyakorláshoz szükséges intézkedésekről és azok ütemezett \r\n"
				+ "forrásigényéről.\r\n"
				+ "Felelős: építési és közlekedési miniszter \r\n"
				+ "gazdaságfejlesztési miniszter\r\n"
				+ "Határidő: 2023. június 30.\r\n"
				+ "  Orbán Viktor s. k.,");
		decrees.add(decree5);
		
		Decree decree6 = new Decree(29, 
				"A Kormány 1185/2023. (V. 8.) Korm. határozata", 
				"A Kormány 1185/2023. (V. 8.) Korm. határozata\r\n"
				+ "Esztergom Megyei Jogú Város árvízi biztonsága érdekében szükséges kormányzati intézkedésekről\r\n"
				+ " 1. A Kormány\r\n"
				+ "a) egyetért az  Esztergom Megyei Jogú Város árvízvédelmi fejlesztésére irányuló beruházás (a továbbiakban: \r\n"
				+ "Beruházás) részére bemutatott koncepció szerinti előkészítésével;\r\n"
				+ "b) a Beruházás felelőseként a belügyminisztert jelöli ki;\r\n"
				+ "c) egyetért azzal, hogy a  Beruházást az  Országos Vízügyi Főigazgatóság készítse elő Esztergom Megyei Jogú \r\n"
				+ "Város Önkormányzatának konzorciumi partneri bevonásával;\r\n"
				+ "d) felhívja a  pénzügyminisztert, hogy a  belügyminiszter kezdeményezése alapján a  Beruházás előkészítési \r\n"
				+ "feladatainak végrehajtása érdekében gondoskodjon 230 000 000 forint többletforrás biztosításáról \r\n"
				+ "a  Magyarország 2023. évi központi költségvetéséről szóló 2022. évi XXV. törvény 1.  melléklet \r\n"
				+ "XIV. Belügyminisztérium fejezet, 17. Vízügyi igazgatóságok cím javára;\r\n"
				+ "Felelős: pénzügyminiszter\r\n"
				+ " belügyminiszter\r\n"
				+ "Határidő: a felmerülés ütemében\r\n"
				+ "e) felhívja a belügyminisztert, hogy\r\n"
				+ "ea) a d) alpont szerinti előkészítési feladatok végrehajtását követően, valamint\r\n"
				+ "eb) a  már teljes körű engedéllyel rendelkező védvonalszakaszra lefolytatott feltételes közbeszerzési \r\n"
				+ "eljárás eredménye alapján\r\n"
				+ " készítsen előterjesztést a Kormány részére a Beruházás megvalósításáról, a kapcsolódó kötelezettségvállalási \r\n"
				+ "összegek és a kifizetések ütemezésének bemutatásával;\r\n"
				+ "Felelős: belügyminiszter\r\n"
				+ "Határidő: az ea) pont tekintetében az előkészítési feladatok végrehajtását követően 30 napon belül\r\n"
				+ " az eb)  pont tekintetében a  kivitelezésre irányuló feltételes közbeszerzési eljárás lezárását \r\n"
				+ "követően azonnal\r\n"
				+ "f ) – a  2014–2020 programozási időszakban az  egyes európai uniós alapokból származó támogatások \r\n"
				+ "felhasználásának rendjéről szóló 272/2014. (XI. 5.) Korm. rendelet 4.  § (1)  bekezdés c)  pontjában \r\n"
				+ "meghatározott jogkörében eljárva – egyetért az  „Esztergom árvízvédelmének fejlesztése I. ütem” című \r\n"
				+ "kiemelt projektnek (a továbbiakban: projekt) a Környezeti és Energiahatékonysági Operatív Program \r\n"
				+ "éves fejlesztési keretének megállapításáról szóló 1084/2016. (II. 29.) Korm. határozatból [a továbbiakban: \r\n"
				+ "1084/2016. (II. 29.) Korm. határozat] való törlésével;\r\n"
				+ "2618 M A G Y A R  K Ö Z L Ö N Y  •  2023. évi 67. szám \r\n"
				+ "A Magyar Közlönyt az Igazságügyi Minisztérium szerkeszti.\r\n"
				+ "A szerkesztésért felelős: dr. Salgó László Péter.\r\n"
				+ "A szerkesztőség címe: 1051 Budapest, Nádor utca 22.\r\n"
				+ "A Magyar Közlöny hiteles tartalma elektronikus dokumentumként a https://www.magyarkozlony.hu honlapon érhető el.\r\n"
				+ "g) felhívja a  területfejlesztésért felelős minisztert, hogy az  irányító hatóság útján gondoskodjon a  projekt \r\n"
				+ "Támogatási szerződésétől történő elállásról;\r\n"
				+ "Felelős: területfejlesztési miniszter\r\n"
				+ "Határidő: az e kormányhatározat közzétételét követően azonnal\r\n"
				+ "h) egyetért azzal, hogy a projekt európai uniós forrás terhére el nem számolható 791 737 966 forint támogatási \r\n"
				+ "összege – az Uniós fejlesztések fejezetbe tartozó fejezeti és központi kezelésű előirányzatok felhasználásának \r\n"
				+ "rendjéről szóló 590/2022. (XII. 28.) Korm. rendelet 9.  § (1)  bekezdés m)  pontja alapján – a  központi \r\n"
				+ "költségvetés XIX. Uniós fejlesztések fejezet KEHOP előirányzat hazai társfinanszírozáson felüli felhasználásával \r\n"
				+ "kerüljön finanszírozásra.\r\n"
				+ " 2. Ez a határozat a közzétételét követő napon lép hatályba.\r\n"
				+ " 3. Hatályát veszti az 1084/2016. (II. 29.) Korm. határozat 2. mellékletében foglalt táblázat 64. sora.\r\n"
				+ "  Orbán Viktor s. k.,");		
		decrees.add(decree6);
		
		return decrees;
	}
}
