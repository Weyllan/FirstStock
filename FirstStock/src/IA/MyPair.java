/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IA;

/**
 *
 * @author mathieu
 */
public class MyPair
{
    private final Double key;
    private final Double value;

    public MyPair(double aKey, double aValue)
    {
        key   = aKey;
        value = aValue;
    }

    public Double key()   { return key; }
    public Double value() { return value; }
}
