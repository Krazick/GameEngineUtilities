package geUtilities.utilites;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName ("Game Engine Utilities Test Suite")
@SelectPackages ( { "geUtilities", "geUtilities.xml", "geUtilities.checksum" } )
class GEUtilitiesTestSuite {

}
