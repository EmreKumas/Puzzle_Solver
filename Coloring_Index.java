public class Coloring_Index{

    private int[][] indexArray;

    Coloring_Index(int length){

        indexArray = new int[length][2];

    }

    void setIndexArray(int[][] referenceArray){

        for(int i = 0; i < indexArray.length; i++){

            indexArray[i][0] = referenceArray[i][0];
            indexArray[i][1] = referenceArray[i][1];

        }

    }

    int[][] getIndexArray(){

        return indexArray;

    }

}
