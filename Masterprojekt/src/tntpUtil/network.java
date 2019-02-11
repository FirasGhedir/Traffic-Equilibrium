package tntpUtil;


public enum network
{
	Anaheim("Anaheim"), 
	Austin("Austin"),
	Barcelona("Barcelona"),
	BerlinCenter("Berlin-Center"),
	BerlinFriedrichshain("Berlin-Friedrichshain"),
	BerlinMitteCenter("Berlin-Mitte-Center"),
	BerlinMittePrenzlauerbergFriedrichshainCenter("Berlin-Mitte-Prenzlauerberg-Friedrichshain-Center"),
	BerlinPrenzlauerbergCenter("Berlin-Prenzlauerberg-Center"),
	BerlinTiergarten("Berlin-Tiergarten"),
	BirminghamEngland("Birmingham-England"),
	BraessExample("Braess-Example"),
	ChicagoSketch("Chicago-Sketch"),
	EasternMassachusetts("Eastern-Massachusetts"),
	HessenAsymmetric("Hessen-Asymmetric"),
	Philadelphia("Philadelphia"),
	SiouxFalls("SiouxFalls"),
	TerrassaAsymmetric("Terrassa-Asymmetric"),
	Winnipeg("Winnipeg"),
	WinnipegAsymmetric("Winnipeg-Asymmetric"),
	chicagoregional("chicago-regional");

	public final String name;

	network(String data){
		this.name=data;
	}

	public String getName(){
		return this.name;
	}
}