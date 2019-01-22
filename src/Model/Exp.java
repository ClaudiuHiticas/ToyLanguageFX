package Model;

import Exceptions.DivisionByZero;
import Exceptions.OperatorNotFound;
import Exceptions.VariableNotFound;

abstract class Exp {
    abstract int eval(MyIDictionary<String, Integer> tbl, MyIRandIntKeyDict<Integer> heap) throws DivisionByZero, VariableNotFound, OperatorNotFound;
}