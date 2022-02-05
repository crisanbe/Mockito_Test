package com.cursosandroidant.calculator

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CalculatorUtilsMockTest {

    //SIMLULAR OPERACIONES CON @Mock
    @Mock
    lateinit var operations: Operations

    @Mock
    lateinit var listener: OnResolveListener

    lateinit var calculatorUtils: CalculatorUtils

    @Before
    fun setup() {
        calculatorUtils = CalculatorUtils(operations, listener)
    }

    @Test
    fun cacl_callcheckOrResolve_noReturn() {
        val operation = "-5*2.5"
        val isFromResolve = true
        calculatorUtils.checkOrResolve(operation, isFromResolve)
        Mockito.verify(operations).tryResolve(
            operation,
            isFromResolve,
            listener
        )//verificar que se cumpla la operaciones segun con calculatorUtils
    }

    @Test//valida el operador menos despues del mas
    fun calc_callAddOperator_validSub_noReturn() {
        val operator = "-"
        val operation = "4+"//4+-3  ejemplo de validacion operacion matematica validad
        var isCorrect = false
        calculatorUtils.addOperator(operator, operation) {
            isCorrect = true
        }
        assertTrue(isCorrect)
    }

    @Test//Metodo invalido no debe retornar otro operador menos
    fun calc_callAddOperator_invalidSub_noReturn() {
        val operator = "-"
        val operation = "4+-"//4+-3  ejemplo de validacion operacion matematica validad
        var isCorrect = false
        calculatorUtils.addOperator(operator, operation) {
            isCorrect = true
        }
        assertFalse(isCorrect)
    }

    @Test//adiccionar un punto 3*2. correcto
    fun calc_callAddPoint_firstPoint_no_Return() {
        val operator = "3*2"
        var isCorret = false
        calculatorUtils.addPoint(operator) {
            isCorret = true
        }
        assertTrue(isCorret)
        Mockito.verifyNoInteractions(operations)//Verificar que nunca he llamado a operations En caso de que no tenga un punto o que lo tenga
    }

    @Test//adiccionar un segundo punto 3*2..
    fun calc_callAddPoint_SecontPoint_noReturn() {
        val operation = "3.5*2"
        val operator = "*"
        var isCorret = false

        //Gracias a esta funcion de Mockito WHEN nos permite simular nuestrar funciones con los operadores.
        Mockito.`when`(operations.getOperator(operation)).thenReturn("*")
        Mockito.`when` (operations.divideOperation(operator,operation)).thenReturn(arrayOf("2.5","2"))

        calculatorUtils.addPoint(operation){
            isCorret = true
        }
        assertTrue(isCorret)
        verify(operations).getOperator(operation)
        verify(operations).divideOperation(operator,operation)
    }
}
