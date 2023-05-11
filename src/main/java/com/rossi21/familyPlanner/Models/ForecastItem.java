package com.rossi21.familyPlanner.Models;

public class ForecastItem {
    private String date;
    private Day day;

    // Add other necessary properties, constructors, getters, and setters

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public static class Day {
        private double maxtemp_f;
        private double mintemp_f;
        private Condition condition;
		
        public double getMaxtemp_f() {
			return maxtemp_f;
		}
		public void setMaxtemp_f(double maxtemp_f) {
			this.maxtemp_f = maxtemp_f;
		}
		public double getMintemp_f() {
			return mintemp_f;
		}
		public void setMintemp_f(double mintemp_f) {
			this.mintemp_f = mintemp_f;
		}
		public Condition getCondition() {
			return condition;
		}
		public void setCondition(Condition condition) {
			this.condition = condition;
		}

        
    }

    public static class Condition {
    	private String text;
        private String icon; // Add 'icon' property to store the weather icon URL

        // Add other necessary properties, constructors, getters, and setters

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }
}
