//Define enum
enum SquareType {
    NONE, X, O
}

class BoardSquare {

    //type field
    private SquareType type = SquareType.NONE;

    //Set and get
    SquareType getType() {
        return type;
    }

    void setType(SquareType type) {
        this.type = type;
    }
}
