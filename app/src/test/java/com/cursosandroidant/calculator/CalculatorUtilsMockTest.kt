package com.cursosandroidant.calculator

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CalculatorUtilsMockTest {
    @Mock
    lateinit var operations: Operations

    @Mock
    lateinit var listener: OnResolveListener

    lateinit var calculatorUtils: CalculatorUtils

    @Before
    fun setup(){
        calculatorUtils = CalculatorUtils(operations, listener)
    }

    @Test
    fun cacl_callcheckOrResolve_noReturn(){
        val operation = "-5*2.5"
        val isFromResolve = true
        calculatorUtils.checkOrResolve(operation, isFromResolve)
        Mockito.verify(operations).tryResolve(operation,isFromResolve,listener)
    }

    @Test
    fun calc_callAddOperator_validSub_noReturn(){
        val operator = "-"
        val operation = "4+"//4+-3  ejemplo de validacion operacion matematica validad
        var isCorrect = false
        calculatorUtils.addOperator(operator, operation){
            isCorrect = true
        }
        assertTrue(isCorrect)
    }

}