package geUtilities.checksum;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import checksum.ChecksumCalc;
import geUtilities.GUI;

class ChecksumCalcTests {
	ChecksumCalc checksumCalc;
	
	@BeforeEach
	void setUp () throws Exception {
		checksumCalc = new ChecksumCalc ();
	}

	@Test
	@DisplayName ("Checksum Tests")
	void checksumTests () {
		String tMessage;
		String tChecksum;
		
		tMessage = GUI.NULL_STRING;
		tChecksum = checksumCalc.MD5 (tMessage);
		assertEquals (GUI.NULL_STRING, tChecksum);

		tMessage = GUI.EMPTY_STRING;
		tChecksum = checksumCalc.MD5 (tMessage);
		assertEquals ("d41d8cd98f00b204e9800998ecf8427e", tChecksum);

		tMessage = "This is a test of the Checksum Routine";
		tChecksum = checksumCalc.MD5 (tMessage);
		assertEquals ("bcd6504ea437f52b01416a81df4e5968", tChecksum);

		String tPrivateCompany1TestXML = "<Private id=\"802\" name=\"TEST-Champlain &amp; St. Lawrence\" " +
				"    abbrev=\"TEST-C&amp;SL\" cost=\"40\" \n" + 
				"    revenue=\"10\" homeCell1=\"B20\" homeLocation1=\"7\" homeLocation2=\"12\" \n" + 
				"    note=\"A Corporation owning the C&amp;SL may lay a tile onC&amp;SL's hex even if this hex is not connected to the Corporation's Railhead. This free tile placement is in addition to the Corporation's tile placement — For this turn only the Corporation may play two tiles. The tile played on the C&amp;SL hex does not have to connect to any existing adjacent track.\"\n" + 
				"    special=\"Free Tile Placement\">\n" + 
				"    <Benefits>\n" + 
				"        <Benefit actorType=\"Share Company\" class=\"ge18xx.company.benefit.TilePlacementBenefit\" extra=\"true\" mapCell=\"B20\" cost=\"0\" passive=\"false\"/>\n" + 
				"		    </Benefits>\n" + "		<Certificate director=\"YES\" percentage=\"100\"\n" + 
				"			allowedOwners=\"IPO,Player,Share\" />\n" + 
				"    </Private>\n";
		tChecksum = checksumCalc.MD5 (tPrivateCompany1TestXML, ChecksumCalc.STRIP_WHITESPACE);
		assertEquals ("d3d2b5b7a09f3b544e8c9dcf3daeaf91", tChecksum);

		String tGameInfoMacXML = "<GameInfo gameID=\"2024-08-20-1506\" name=\"1830\">\n" + 
				"    <VariantEffects>\n" + 
				"        <VariantEffect action=\"Allow M&amp;H Exchange at Operating Round Start\" benefitName=\"QUERY EXCHANGE\" class=\"ge18xx.game.variants.EnableBenefitVEffect\" companyID=\"1004\" defaultEffect=\"true\" hide=\"false\" id=\"501\" name=\"Query M&amp;H Exchange\" state=\"true\"/>\n" + 
				"        <VariantEffect action=\"Require Share Company to Operate before Sale\" attributeName=\"operateBeforeSale\" class=\"ge18xx.game.variants.ModifyGameInfoVEffect\" defaultEffect=\"true\" hide=\"false\" id=\"801\" name=\"Operate Before Sale\" state=\"true\"/>\n" + 
				"        <VariantEffect action=\"Random Player Start Order\" attributeName=\"randomizeStartOrder\" class=\"ge18xx.game.variants.ModifyGameInfoVEffect\" defaultEffect=\"true\" hide=\"false\" id=\"901\" name=\"Randomize Start Order\" state=\"false\"/>\n" + 
				"        <VariantEffect action=\"Set Diesel Train Quantity to 6 (Standard)\" class=\"ge18xx.game.variants.SetTrainCountVEffect\" defaultEffect=\"true\" hide=\"false\" id=\"202\" name=\"Standard\" quantity=\"6\" state=\"false\" trainName=\"Diesel\"/>\n" + 
				"    </VariantEffects>\n" + 
				"</GameInfo>\n";
		tChecksum = checksumCalc.MD5 (tGameInfoMacXML, ChecksumCalc.STRIP_WHITESPACE);
		assertEquals ("2de6c1db22bacf3e22dc655cf74d7303", tChecksum);

		String tGameInfoWindowsXML = "<GameInfo gameID=\"2024-08-20-1506\" name=\"1830\">\r\n" + 
				"<VariantEffects>\r\n" + 
				"<VariantEffect action=\"Allow M&amp;H Exchange at Operating Round Start\" benefitName=\"QUERY EXCHANGE\" class=\"ge18xx.game.variants.EnableBenefitVEffect\" companyID=\"1004\" defaultEffect=\"true\" hide=\"false\" id=\"501\" name=\"Query M&amp;H Exchange\" state=\"true\"/>\r\n" + 
				"<VariantEffect action=\"Require Share Company to Operate before Sale\" attributeName=\"operateBeforeSale\" class=\"ge18xx.game.variants.ModifyGameInfoVEffect\" defaultEffect=\"true\" hide=\"false\" id=\"801\" name=\"Operate Before Sale\" state=\"true\"/>\r\n" + 
				"<VariantEffect action=\"Random Player Start Order\" attributeName=\"randomizeStartOrder\" class=\"ge18xx.game.variants.ModifyGameInfoVEffect\" defaultEffect=\"true\" hide=\"false\" id=\"901\" name=\"Randomize Start Order\" state=\"false\"/>\r\n" + 
				"<VariantEffect action=\"Set Diesel Train Quantity to 6 (Standard)\" class=\"ge18xx.game.variants.SetTrainCountVEffect\" defaultEffect=\"true\" hide=\"false\" id=\"202\" name=\"Standard\" quantity=\"6\" state=\"false\" trainName=\"Diesel\"/>\r\n" + 
				"</VariantEffects>\r\n" + 
				"</GameInfo>\r\n\n";
		tChecksum = checksumCalc.MD5 (tGameInfoWindowsXML, ChecksumCalc.STRIP_WHITESPACE);
		assertEquals ("2de6c1db22bacf3e22dc655cf74d7303", tChecksum);
	}
}
