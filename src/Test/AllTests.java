package Test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ActivationFuncTest.class, DfuncTest.class, NormalizeTest.class, SubtractTest.class, ElemMultiplyTest.class, ScalarMultiplyTest.class, ClsToArrayTest.class })
public class AllTests {

}
