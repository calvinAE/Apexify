package com.example.apexify

import org.junit.Assert.*
import org.junit.Test
import org.hamcrest.MatcherAssert


class InputUtilTest{

    @Test
    fun `Empty input returns false`(){
        val input = InputUtil.validInput(
            primary = "",
            secondary = "",
            name = "",
        )
        assert(false)

    }
}