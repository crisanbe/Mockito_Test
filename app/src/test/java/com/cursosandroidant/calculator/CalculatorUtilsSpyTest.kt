package com.cursosandroidant.calculator

import org.junit.Assert
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CalculatorUtilsSpyTest {

    //todo @Spy inicializamos nuestra clase Operations
    //todo- y asi podremos reutilizarlo en todas nuestras funciones
    @Spy
    lateinit var operations: Operations

    @Mock
    lateinit var listener: OnResolveListener

    lateinit var calculatorUtils: CalculatorUtils

    @Before//(Antes)
    fun setup() {
        calculatorUtils = CalculatorUtils(operations, listener)
    }

    @Test//(verifyNoInteractions):adiccionar un punto 3*2. correcto
    fun calc_callAddPoint_validSeconPoint_no_Returns() {
        val operation = "3.5x2"
        val operator = "x"
        var isCorret = false

        calculatorUtils.addPoint(operation) {
            isCorret = true
        }
        assertTrue(isCorret)
        verify(operations).getOperator(operation)
        verify(operations).divideOperation(operator, operation)
    }

    @Test//(thenReturn):adiccionar un segundo punto 3*2..
    fun calc_callAddPoint_invalidSecontPoint_noReturns() {
        val operation = "3.5x2."//aqui ya no me debe permitir agregar un tercer punto, por qie ya tinen los dos
        val operator = "x"
        var isCorret = false

        calculatorUtils.addPoint(operation) {
            isCorret = true
        }
        assertFalse(isCorret)
        verify(operations).getOperator(operation)
        verify(operations).divideOperation(operator, operation)
    }
}

