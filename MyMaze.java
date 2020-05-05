// written by Nilesh Domah

import java.util.Random;

public class MyMaze{
    Cell[][] maze;

    public class Index {
        int i;
        int j;
        public Index(int i, int j) {
            this.i = i;
            this.j = j;
        }
    } // end Index

    public MyMaze(int rows, int cols) {
        // initialize border cells as already visited
        maze = new Cell[rows + 2][cols + 2];

        // create a new cell object for each index
        for (int i = 0; i < rows + 2; i++) {
            for (int j = 0; j < cols + 2; j++) {
                maze[i][j] = new Cell();
            }
        }

        for (int i = 0; i < rows + 2; i++) {
            maze[i][0].setVisited(true);
            maze[i][cols + 1].setVisited(true);
        }

        for (int j = 0; j < cols + 2; j++) {
            maze[0][j].setVisited(true);
            maze[rows+1][j].setVisited(true);
        }

        for (int i = 0; i < rows + 2; i++) {
            for (int j = 0; j < cols + 2; j++) {
                maze[i][j].setRight(true);
                maze[i][j].setBottom(true);
            }
        }
    } // end MyMaze constructor

    public static MyMaze makeMaze(int rows, int cols) {
        MyMaze myMaze = new MyMaze(rows, cols);
        Stack1Gen<Index> stack = new Stack1Gen<Index>();
        Index start = myMaze.new Index(1, 1);
        stack.push(start);
        myMaze.maze[1][1].setVisited(true);

        Random rand = new Random();

        int x, y;

        while (!stack.isEmpty()) {
            Index idx = stack.top();

            x = idx.i;
            y = idx.j;

            int count = 0;

            if (!myMaze.maze[x][y+1].getVisited()) {
                count++;
            }

            if (!myMaze.maze[x+1][y].getVisited()) {
                count++;
            }

            if (!myMaze.maze[x][y-1].getVisited()) {
                count++;
            }

            if (!myMaze.maze[x-1][y].getVisited()) {
                count++;
            }

            if (count == 0) {
                stack.pop();
                continue;
            }


            // Generate random integers in range 0 to 4 (for neighbours left, right, bottom, top)
            int r = rand.nextInt(count);

            int k = 0;

            Index neighbors[] = new Index[count];

            if (!myMaze.maze[x][y+1].getVisited()) {
                neighbors[k++] = myMaze.new Index(x, y+1);
            }

            if (!myMaze.maze[x+1][y].getVisited()) {
                neighbors[k++] = myMaze.new Index(x+1, y);
            }

            if (!myMaze.maze[x][y-1].getVisited()) {
                neighbors[k++] = myMaze.new Index(x, y-1);
            }

            if (!myMaze.maze[x-1][y].getVisited()) {
                neighbors[k++] = myMaze.new Index(x-1, y);
            }

            Index finalNeighbor = neighbors[r];

            int ni = finalNeighbor.i;
            int nj = finalNeighbor.j;

            myMaze.maze[ni][nj].setVisited(true);
            stack.push(finalNeighbor);

            if (ni == x && nj == y + 1) {
                myMaze.maze[x][y+1].setBottom(false);
                continue;
            }

            if (ni == x + 1 && nj == y) {
                myMaze.maze[x+1][y].setRight(false);
                continue;
            }

            if (ni == x && nj == y - 1) {
                myMaze.maze[x][y-1].setBottom(false);
                continue;
            }

            if (ni == x - 1 && nj == y) {
                myMaze.maze[x-1][y].setRight(false);
                continue;
            }
        }

        for (int i = 1; i < rows+1; i++) {
            for (int j = 1; j < cols+1; j++) {
                myMaze.maze[i][j].setVisited(false);
            }
        }

        return myMaze;
    } // end makeMaze

    public void printMaze(boolean path) {
        System.out.println();
        String cell2;
        String cell ="|";
        boolean two=false;
        int rows = this.maze.length;
        int cols = this.maze[0].length;
        for (int i = 0; i < cols; i++) {
            cell=cell+"---|";
        }
        System.out.print(cell);
        System.out.println();
        for (int i = 0; i < rows; i++) {
            cell ="";
            cell2="";
            for (int j = 0; j < cols; j++) {

                if (j == 0) {
                    if (i == 0) {
                        if (path) {
                            if (this.maze[i][j].getVisited()) {
                                cell = cell + "  * ";
                            } else {
                                cell = cell + "    ";
                            }
                        } else {
                            cell = cell + "    ";
                        }
                        if (this.maze[i][j].getRight()) {
                            cell = cell + "|";
                        } else {
                            cell = cell + " ";
                        }
                    } else {
                        if (path) {
                            if (this.maze[i][j].getVisited()) {
                                cell = cell + "| * ";
                            } else {
                                cell = cell + "|   ";
                            }
                        } else {
                            cell = cell + "|   ";
                        }
                        if (this.maze[i][j].getRight()) {
                            cell = cell + "|";
                        } else {
                            cell = cell + " ";
                        }
                    }
                }

                else {
                    if (path) {
                        if (this.maze[i][j].getVisited()) {
                            cell = cell + " * ";
                        } else {
                            cell = cell + "   ";
                        }
                    } else {
                        cell = cell + "   ";
                    }
                    if (this.maze[i][j].getRight()) {
                        cell = cell + "|";
                    } else {
                        cell = cell + " ";
                    }
                }


                if (j == 0) {
                    if (i == 0) {
                        if (this.maze[i][j].getBottom()) {
                            cell2 = cell2 + " ---";
                        } else {
                            cell2 = cell2 + "    ";
                        }
                        if (this.maze[i][j].getRight()) {
                            cell2 = cell2 + "|";
                        } else {
                            cell2 = cell2 + " ";
                        }

                    } else {

                        if (this.maze[i][j].getBottom()) {
                            cell2 = cell2 + "|---";
                        } else {
                            cell2 = cell2 + "|   ";
                        }
                        if (this.maze[i][j].getRight()) {
                            cell2 = cell2 + "|";
                        } else {
                            cell2 = cell2 + " ";
                        }
                    }
                } else {
                    if (this.maze[i][j].getBottom()) {
                        cell2 = cell2 + "---";
                    } else {
                        cell2 = cell2 + "   ";
                    }
                    if (this.maze[i][j].getRight()) {
                        cell2 = cell2 + "|";
                    } else {
                        cell2 = cell2 + " ";
                    }
                }

            }
            System.out.println(cell);
            System.out.println(cell2);
        }
    } // end printMaze

    public void solveMaze() {
        Q1Gen<Index> queue = new Q1Gen<Index>();

        Index start = this.new Index(1, 1);
        queue.add(start);
        Index cur;

        int rows = this.maze.length;
        int cols = this.maze[0].length;
        boolean isSolved = false;
        int x, y;
        while (!queue.isEmpty()) {
            cur = queue.remove();

            x = cur.i;
            y = cur.j;

            this.maze[x][y].setVisited(true);

            if (x == rows && y == cols) {
                isSolved = true;
                break;
            }

            // add all neighbours
            if (!this.maze[x][y+1].getVisited()) {
                queue.add(this.new Index(x, y+1));
            }

            if (!this.maze[x+1][y].getVisited()) {
                queue.add(this.new Index(x+1, y));
            }

            if (!this.maze[x][y-1].getVisited()) {
                queue.add(this.new Index(x, y-1));
            }

            if (!this.maze[x-1][y].getVisited()) {
                queue.add(this.new Index(x-1, y));
            }
        }
        this.printMaze(true);
    } // end solveMaze


    public static void main(String[] args){
        /* Any testing can be put in this main function */
        MyMaze myMaze = MyMaze.makeMaze(5, 6);
        myMaze.printMaze(false);
        myMaze.solveMaze();
    } // end main
} // end MyMaze
