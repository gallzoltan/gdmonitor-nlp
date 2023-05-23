import os
import sys
import nlpcloud
from dotenv import load_dotenv

def getSummary(cnt):
  load_dotenv()
  nlp_key = os.getenv('NLP_KEY')
  client = nlpcloud.Client("bart-large-cnn", nlp_key, gpu=False, lang="hu")
  result = client.summarization(cnt, size="small")
  print(result)

def main():
  args = sys.argv[1:]  
  if len(args) == 2 and args[0] == '-cnt':    
    getSummary(args[1])
    #getSummary("M A G YA R K Ö Z L Ö N Y • 2023. évi 67. szám 2615 A Kormány 1183/2023. (V. 8.) Korm. határozata az önkormányzatok adósságot keletkeztető, valamint kezesség-, illetve garanciavállalásra vonatkozó ügyleteihez történő 2023. márciusi előzetes kormányzati hozzájárulásról A Kormány a Magyarország gazdasági stabilitásáról szóló 2011. évi CXCIV. törvény 10. § (1) bekezdése alapján, az adósságot keletkeztető ügyletekhez történő hozzájárulás részletes szabályairól szóló 353/2011. (XII. 30.) Korm. rendeletben foglaltakra és a veszélyhelyzet idején az önkormányzatok és gazdasági társaságaik energiahatékonyság javítását szolgáló fejlesztésekhez történő adósságot keletkeztető ügyleteinek eltérő szabályairól szóló 442/2022. (XI. 7.) Korm. rendeletben foglaltakra tekintettel a következő határozatot hozza: A Kormány az ügyletről az 1. mellékletben foglaltak szerint dönt, az engedélyezett ügylet 2023. évben történő megkötéséhez az a)–c) pontban foglalt kiegészítő feltételekkel járul hozzá: a) az önkormányzat az 1. melléklet szerint engedélyezett ügyletérték teljes összegétől lefelé eltérhet, ha ez nem okozza ezen érték valamelyikének növekedését egyik évben sem; b) az önkormányzat egy adott év végéig legfeljebb akkora összegű adósságot keletkeztethet az ügyletből, mint amekkora az 1. melléklet szerint az ügyletből az adott év végéig keletkező kumulált adósságösszeg; c) fejlesztési célú ügylet esetében az ügyletet csak az 1. mellékletben szereplő fejlesztésre lehet fordítani. Orbán Viktor s. k., miniszterelnök")
  
if __name__ == "__main__":
  main()
