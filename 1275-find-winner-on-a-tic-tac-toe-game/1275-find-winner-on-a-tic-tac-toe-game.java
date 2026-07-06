class Solution {
    public String tictactoe(int[][] moves) {
        int[] lines = new int[8];
        int player = -1;
        for (int[] m : moves) {
            player *= -1;
            lines[m[0]] += player;
            lines[m[1] + 3] += player;
            if (m[0] == m[1]) lines[6] += player;
            if (m[0] + m[1] == 2) lines[7] += player;
        }
        for (int l : lines)
            if (Math.abs(l) == 3)
                return player == 1 ? "A" : "B";
        return moves.length == 9 ? "Draw" : "Pending";
    }
}