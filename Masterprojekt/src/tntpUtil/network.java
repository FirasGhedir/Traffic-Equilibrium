package tntpUtil;

/**
 * Universität Ulm
 * 
 * Projekt Algorithm Engineering-Projekt --- WiSe 2018/19
 * 
 * @author Firas Ghedir (firas.ghedir@uni-ulm.de)
 * @author Julian Bestler (julian.bestler@uni-ulm.de)
 * 
 * @version 1.0
 * 
 *          _____________________________________________
 * 
 *          Enumerations for transportation networks
 */
public enum network{
	
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
	chicagoregional("chicago-regional"),
	other("other");

	public final String name;

	network(String data){
		this.name=data;
	}

	public String getName(){
		return this.name;
	}
}