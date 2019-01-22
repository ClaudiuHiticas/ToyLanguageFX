package Model;

import Exceptions.DivisionByZero;
import Exceptions.OperatorNotFound;
import Exceptions.VariableNotFound;

public class BoolExp extends Exp {
    private String compareOperator;
    private Exp firstExpression;
    private Exp secondExpression;

    public BoolExp(String compareOperator, Exp firstExpression, Exp secondExpression) {
        this.compareOperator = compareOperator;
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
    }

    @Override
    int eval(MyIDictionary<String, Integer> tbl, MyIRandIntKeyDict<Integer> heap) throws DivisionByZero, VariableNotFound, OperatorNotFound {
        int finalResult;
        int firstEvaluation = this.firstExpression.eval(tbl, heap);
        int secondEvaluation = this.secondExpression.eval(tbl, heap);
        // exp1 < exp2
        // exp1<=exp2
        // exp1==exp2
        // exp1!=exp2
        // exp1> exp2
        // exp1>=exp2
        switch (compareOperator) {
            case "<":
                finalResult = firstEvaluation < secondEvaluation ? 1 : 0;
                break;
            case "<=":
                finalResult = firstEvaluation <= secondEvaluation ? 1 : 0;
                break;
            case "==":
                finalResult = firstEvaluation == secondEvaluation ? 1 : 0;
                break;
            case "!=":
                finalResult = firstEvaluation != secondEvaluation ? 1 : 0;
                break;
            case ">":
                finalResult = firstEvaluation > secondEvaluation ? 1 : 0;
                break;
            case ">=":
                finalResult = firstEvaluation >= secondEvaluation ? 1 : 0;
                break;
            default:
                throw new OperatorNotFound();

        }
        return finalResult;
    }

    @Override
    public String toString() {
        return "comp(" + this.firstExpression.toString() + this.compareOperator + this.secondExpression + ")";
    }
}
