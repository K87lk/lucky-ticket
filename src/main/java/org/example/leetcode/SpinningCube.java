package org.example.leetcode;

import java.util.Arrays;

public class SpinningCube {

    // Structure to hold 3D coordinates
    static class Point3D {
        float x, y, z;

        Point3D(float x, float y, float z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    // Structure to hold 2D coordinates for projection
    static class Point2D {
        int x, y;

        Point2D(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // Function to project a 3D point to 2D
    static Point2D project(Point3D point) {
        float scale = 4.0f / (4.0f + point.z); // Simple perspective projection
        int projectedX = (int) (point.x * scale * 20 + 40);
        int projectedY = (int) (-point.y * scale * 10 + 10);
        return new Point2D(projectedX, projectedY);
    }

    // Function to draw the cube
    static void drawCube(float angle) {
        // Define the 8 vertices of the cube
        Point3D[] vertices = {
                new Point3D(-1, -1, -1), new Point3D(1, -1, -1), new Point3D(1, 1, -1), new Point3D(-1, 1, -1),
                new Point3D(-1, -1, 1), new Point3D(1, -1, 1), new Point3D(1, 1, 1), new Point3D(-1, 1, 1)
        };

        // Rotate and project each vertex
        for (int i = 0; i < vertices.length; i++) {
            // Rotate around the Y-axis
            float x = vertices[i].x * (float) Math.cos(angle) - vertices[i].z * (float) Math.sin(angle);
            float z = vertices[i].x * (float) Math.sin(angle) + vertices[i].z * (float) Math.cos(angle);
            vertices[i].x = x;
            vertices[i].z = z;

            // Rotate around the X-axis
            float y = vertices[i].y * (float) Math.cos(angle) - vertices[i].z * (float) Math.sin(angle);
            z = vertices[i].y * (float) Math.sin(angle) + vertices[i].z * (float) Math.cos(angle);
            vertices[i].y = y;
            vertices[i].z = z;
        }

        // Create a 2D array to store the projection
        char[][] screen = new char[20][80];
        for (char[] row : screen) {
            Arrays.fill(row, ' '); // Initialize with spaces
        }

        // Project and draw edges between the projected vertices
        Point2D[] projected = new Point2D[8];
        for (int i = 0; i < vertices.length; i++) {
            projected[i] = project(vertices[i]);
        }

        // Define edges of the cube (connecting vertices)
        int[][] edges = {
                {0, 1}, {1, 2}, {2, 3}, {3, 0}, // front face
                {4, 5}, {5, 6}, {6, 7}, {7, 4}, // back face
                {0, 4}, {1, 5}, {2, 6}, {3, 7}  // connecting edges
        };

        // Draw the edges
        for (int[] edge : edges) {
            int start = edge[0];
            int end = edge[1];

            Point2D startPoint = projected[start];
            Point2D endPoint = projected[end];

            // Simple line drawing (Bresenham's algorithm can be implemented for better lines)
            int dx = endPoint.x - startPoint.x;
            int dy = endPoint.y - startPoint.y;
            int steps = Math.max(Math.abs(dx), Math.abs(dy));
            float xIncrement = (float) dx / steps;
            float yIncrement = (float) dy / steps;

            float x = startPoint.x;
            float y = startPoint.y;
            for (int j = 0; j <= steps; j++) {
                if ((int) y >= 0 && (int) y < 20 && (int) x >= 0 && (int) x < 80) {
                    screen[(int) y][(int) x] = '#'; // Draw a pixel
                }
                x += xIncrement;
                y += yIncrement;
            }
        }

        // Print the screen buffer
        System.out.print("\033[H\033[J"); // Clear the console
        for (char[] row : screen) {
            System.out.println(row);
        }
    }

    public static void main(String[] args) {
        float angle = 0.0f;

        // Main loop to animate the cube
        while (true) {
            drawCube(angle);
            angle += 0.1f; // Increment the angle for rotation

            try {
                Thread.sleep(50); // Sleep for 50 milliseconds for a smoother animation
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
