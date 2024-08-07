package dev.harshit.productservice.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CalculatorControllerTest {

    // private AddService addService = Mockito.mock(AddService.class);

    @Autowired
    private CalculatorController calculatorController;

    @MockBean
    private AddService addService;

    @Test
    public void testAdd() {
        when(addService.sumFromAddService(anyInt(), anyInt()))
                .thenReturn(15);

        int a = 10;
        int b = 5;
        int expectedResult = 15;

        int result = calculatorController.add(a,b);

        Assertions.assertEquals(expectedResult, result);
    }
}



// JUnit:- Testing framework for Java applications, designed to make the process of writing and
// running tests straightforward and efficient

// Mocking this one -> (int result = addService.sumFromAddService(a, b);), not -> public int add(int a, int b)
// Testing productControllerTest, Don't want addService.sumFromAddService(a, b) to be called, hence mocking
// when(addService.sumFromAddService(10, 5)).thenReturn(15); :- Mocked this method that anytime this is
// called with specific values, return 15


/* *
 * Mockito is a popular Java-based mocking framework used in conjunction with testing frameworks like JUnit.
 * It allows developers to create mock objects for unit testing, enabling the isolation of the code
 * under test and the simulation of various scenarios.

Key Features:-

Mock Creation:
* Mockito.mock(Class<T> classToMock): Creates a mock instance of the specified class.
* @Mock: Annotation to create and inject mocks.

Behavior Stubbing:
* when(mock.method()).thenReturn(value): Specifies the behavior of a mock method when it is called.
* when(mock.method()).thenThrow(exception): Specifies that a mock method should throw an exception.

Verification:
* verify(mock).method(): Verifies that a method on the mock object was called.
* verify(mock, times(n)).method(): Verifies that a method was called a specific number of times.
* verify(mock, never()).method(): Verifies that a method was never called.

Argument Matchers:
* Mockito.any(), Mockito.eq(value): Used to specify flexible argument matching.

Annotations:
* @InjectMocks: Creates an instance of the class and injects the mocks created with @Mock into it.
* @Spy: Creates a spy instance which allows real methods to be called, but still can be stubbed and verified.

InOrder Verification:
* InOrder inOrder = inOrder(mock1, mock2): Verifies that interactions happened in a specific order.
 * */


/* *
 * Assertions are a crucial part of unit testing, as they help verify that the code behaves as expected.
 * In Java, assertions are provided by testing frameworks like JUnit. They allow to compare actual
 * outcomes with expected outcomes, ensuring the correctness of your code.

Common Assertions in JUnit:-

Equality and Identity Assertions:
* assertEquals(expected, actual): Asserts that two values are equal.
* assertNotEquals(unexpected, actual): Asserts that two values are not equal.
* assertSame(expected, actual): Asserts that two references point to the same object.
* assertNotSame(unexpected, actual): Asserts that two references do not point to the same object.

Truth and Falsehood Assertions:
* assertTrue(condition): Asserts that a condition is true.
* assertFalse(condition): Asserts that a condition is false.

Nullity Assertions:
* assertNull(object): Asserts that an object is null.
* assertNotNull(object): Asserts that an object is not null.

Array Assertions:
* assertArrayEquals(expectedArray, actualArray): Asserts that two arrays are equal.

Exception Assertions:
* assertThrows(expectedType, executable): Asserts that executing a piece of code throws an expected exception.

Timeout Assertions:
* assertTimeout(duration, executable): Asserts that executing a piece of code completes before a timeout.
* assertTimeoutPreemptively(duration, executable): Similar to assertTimeout but interrupts the execution
                                                   if the timeout is exceeded.

 * */


/* * anyInt():- Method used as an argument matcher in mock interactions. It allows to specify that any
                integer value is acceptable when matching method calls on mocked objects.
 * */

/* * @MockBean:-
@MockBean is an annotation provided by the Spring Boot Test framework. It is used to add mocks to the
Spring ApplicationContext, which allows to replace a bean with a mock for the duration of a test. This is
particularly useful in integration tests where you want to isolate unit under test from its dependencies.
 * */