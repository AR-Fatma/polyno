import java.util.LinkedList;

public class Polynomial {
    private LinkedList<Term> terms1;
    private LinkedList<Term> terms2;

    public Polynomial() {
        terms1 = new LinkedList<>();
        terms2 = new LinkedList<>();
    }
    public String addpoly(String poly1, String poly2) {
        LinkedList<Term> terms1 = parsePolynomial(poly1);
        LinkedList<Term> terms2 = parsePolynomial(poly2);
        LinkedList<Term> result = new LinkedList<>();
    
        int i = 0, j = 0;
        while (i < terms1.size() && j < terms2.size()) {
            Term term1 = terms1.get(i);
            Term term2 = terms2.get(j);
    
            if (term1.exponent > term2.exponent) {
                result.add(term1);
                i++;
            } else if (term1.exponent < term2.exponent) {
                result.add(term2);
                j++;
            } else {
                int sum = term1.coefficient + term2.coefficient;
                if (sum != 0) {
                    Term newTerm = new Term(sum, term1.exponent);
                    result.add(newTerm);
                }
                i++;
                j++;
            }
        }
    
        while (i < terms1.size()) {
            result.add(terms1.get(i));
            i++;
        }
    
        while (j < terms2.size()) {
            result.add(terms2.get(j));
            j++;
        }
    
        StringBuilder sb = new StringBuilder();
        for (Term term : result) {
            if (term.coefficient >= 0 && sb.length() > 0) {
                sb.append("+");
            }
            sb.append(term.coefficient);
            sb.append("x^");
            sb.append(term.exponent);
            sb.append(" ");
        }
    
        return sb.toString();
    }
    
    private LinkedList<Term> parsePolynomial(String poly) {
        LinkedList<Term> terms = new LinkedList<>();
        String[] termStrings = poly.split(";");
        for (String termString : termStrings) {
            String[] parts = termString.split("x\\^");
            int coefficient = Integer.parseInt(parts[0]);
            int exponent = Integer.parseInt(parts[1]);
            Term term = new Term(coefficient, exponent);
            terms.add(term);
        }
        return terms;
    }
    

 

    private class Term {
        private int coefficient;
        private int exponent;

        public Term(int coefficient, int exponent) {
            this.coefficient = coefficient;
            this.exponent = exponent;
        }
    }
}
