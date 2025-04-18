
    public class Game implements Comparable<Game> {
        private String title;
        private int available;
        private int rented;
    
        // Overloaded constructor
        public Game(String title, int available, int rented) {
            this.title = title;
            this.available = available;
            this.rented = rented;
        }
    
        // Accessor for title
        public String getTitle() {
            return title;
        }
    
        // Mutator for title
        public void setTitle(String title) {
            this.title = title;
        }
    
        // Accessor for available copies
        public int getAvailable() {
            return available;
        }
    
        // Mutator for available copies
        public void setAvailable(int available) {
            this.available = available;
        }
    
        // Accessor for rented copies
        public int getRented() {
            return rented;
        }
    
        // Mutator for rented copies
        public void setRented(int rented) {
            this.rented = rented;
        }
    
        // compareTo method to compare games by title alphabetically
        @Override
        public int compareTo(Game other) {
            return this.title.compareToIgnoreCase(other.title);
        }
    
        // toString method to display the game details in a formatted string
        @Override
        public String toString() {
            return String.format("%-30s %5d %5d", title, available, rented);
        }
    }
      