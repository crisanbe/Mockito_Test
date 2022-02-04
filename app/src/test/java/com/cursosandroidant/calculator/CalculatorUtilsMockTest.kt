package com.cursosandroidant.calculator

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CalculatorUtilsMockTest {
    @Mock
    lateinit var operations: Operations

    @Mock
    lateinit var listener: OnResolveListener

    lateinit var caculatorUtils: CalculatorUtils

    @Before
    fun setup(){
        caculatorUtils = CalculatorUtils(operations, listener)
    }

}