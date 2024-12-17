package visualso.listener;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.io.*;
import java.net.http.HttpRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import org.commonmark.renderer.html.HtmlRenderer;
import org.commonmark.node.*;
import org.commonmark.parser.Parser;
// import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
// import java.awt.event.WindowEvent;
// import java.awt.event.WindowFocusListener;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JEditorPane;
// import javax.swing.BorderFactory;
import javax.swing.JFrame;
// import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
// import javax.swing.UIManager;
// import javax.swing.UnsupportedLookAndFeelException;
// import javax.swing.UIManager;


public class helpLLMtest implements ActionListener{
	// private String helpInfo;
	// private String name;
	// public helpLLMtest(String name,String helpInfo ) {
	// 	super();
	// 	this.name = name;
	// 	this.helpInfo = helpInfo;
	// }

    String helpInfo = "Sorting Alogorithm is a basic concept that every "
			                  + "programmer should have known.\n \n "
			                  + "There are a lot of sorting algorithms, "
			                  + "but to be suitable with our project, we only focus on 6 algorithms: \n"
			                  + "+ Merge Sort\n"
			                  + "+ Quick Sort\n"
			                  + "+ Bubble Sort\n"
			                  + "+ Insertion Sort\n"
			                  + "+ Shell Sort\n"
			                  + "+ Selection Sort\n \n"
			                  + "This application invented aiming to the purpose of visualizing "
			                  + "these alogrithms in a colorful way to help "
			                  + "user understand this concept easier and meet our class "
			                  + "project needs.\n \n"
			                  + "Without loss of generality, we assume that we will sort only Integers, "
			                  + "not necessarily distinct, in non-decreasing order in this visualization.\n\n"
			                  + "Our app is inspired of Visualgo so we named it as VisualSO as "
			                  + "Visual Sorting algorithms.\n \n"
			                  + "Everything you need is:\n "
			                  + "1. Choosing one of 6 algorithms in the blocks to start your journey\n "
			                  + "2. Create your own array or random array by the leftside button\n "
			                  + "3. Click Sort and view it visualizes, the explanation will be demonstrate on "
			                  + "the right side and flow controller at the bottom.\n"
                              + "4. You can also interact with the application through voice mode. \n"
                              + "5. You can ask for help in the chat interface, and the wizard will respond to you.\n"
			                  + "Have fun!";

    public helpLLMtest() {
        super();
    }
	@Override
	public void actionPerformed(ActionEvent e) {
        JFrame chatFrame = new JFrame("Chat with LLM");
        chatFrame.setSize(1500, 850); // Set the width and height to your desired values
        JPanel chatPanel = new JPanel();
        chatPanel.setLayout(new BorderLayout());

        
        JEditorPane conversationArea = new JEditorPane();
        conversationArea.setContentType("text/html");
        conversationArea.setEditable(false);
        conversationArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
        
        String htmlHelp = HTMLparser(helpInfo);


        // Maintain the full HTML content
        StringBuilder chatHistory = new StringBuilder();
        chatHistory.append(htmlHelp);
        chatHistory.append("<html><body>");
        chatHistory.append("<p><b>Sorting Wizard:</b><br>I'm a wizard come to help you with sorting algorithms.</p>");
        chatHistory.append("</body></html>");
        conversationArea.setText(chatHistory.toString());
        chatPanel.add(new JScrollPane(conversationArea), BorderLayout.CENTER);
        
        JTextField inputField = new JTextField();
        inputField.setFont(new Font("Monospaced", Font.PLAIN, 16));
        inputField.setPreferredSize(new Dimension(1500, 60)); // Set the preferred size
        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userInput = inputField.getText();
                inputField.setText("");
                int user_insertPosition = chatHistory.lastIndexOf("</body>");
                // Append user input to the chat
                chatHistory.insert(user_insertPosition, 
                        "<p><b>User:</b><br>" + userInput + "</p>");

                // Update the chat window
                conversationArea.setText(chatHistory.toString());
                
                // Send user input to API and get LLM response
                Thread thread = new Thread(() -> {
                    String llmResponse = sendToApi(userInput);
                    
                    llmResponse = HTMLparser(llmResponse);
                            // .replace("\"", "") // Remove extra quotes
                            // .replace("\\n", "\n") // Convert literal '\n' to newlines
                            // .replaceAll("\\*\\*(.*?)\\*\\*", "<b>$1</b>") // Handle bold text
                            // .replace("\n", "<br>") // Replace newlines with HTML breaks
                            // .replace("\\u003e", ">"); // replace arrows symbols
                            // .replace("<", "&lt;")// Escape HTML special characters
                            // .replace(">", "&gt;")
                            // .replace("&", "&amp;")
                            
                        
                    // Wrap in <pre> and <code> for formatting
                    // llmResponse = "<pre style='font-family: Courier New, monospace; background-color: #f5f5f5; padding: 10px;'>" + 
                    //                 llmResponse;
                    // Append LLM response to the chat
                    int response_insertPosition = chatHistory.lastIndexOf("</body>");
                    chatHistory.insert(response_insertPosition, 
                            "<p><b>Sorting Wizard:</b><br>" + llmResponse + "</p>");
                    
                    // Update the chat window
                    SwingUtilities.invokeLater(() -> conversationArea.setText(chatHistory.toString()));
                });
                thread.start();
                
            }
        });
        chatPanel.add(inputField, BorderLayout.SOUTH);

        chatFrame.add(chatPanel);
        chatFrame.setSize(600, 400);
        chatFrame.setLocationRelativeTo(null);
        chatFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        chatFrame.setVisible(true);

        // chatFrame.addWindowFocusListener(new WindowFocusListener() {
        //     @Override
        //     public void windowGainedFocus(WindowEvent e) {
        //     }
        //     @Override
        //     public void windowLostFocus(WindowEvent a) {
        //         ((JFrame)a.getSource()).dispose();
        //     }
        // });
    }

		// JFrame helpFrame = new JFrame(name);
		// JTextArea helpContent = new JTextArea(helpInfo);
		// helpContent.setOpaque(true);
		// helpContent.setBackground(Color.black);
		// helpContent.setForeground(Color.WHITE);
		// helpContent.setEditable(false);
		// helpContent.setFocusable(false);
		// helpContent.setLineWrap(true);
		// helpContent.setWrapStyleWord(true);
		// helpContent.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		// helpFrame.setSize(500,480);
		// helpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// helpFrame.setLocationRelativeTo(null);
		// helpFrame.setResizable(false);
		// helpFrame.add(helpContent);
		// helpFrame.setVisible(true);
		// helpFrame.addWindowFocusListener(new WindowFocusListener() {
		// 	@Override
		// 	public void windowGainedFocus(WindowEvent e) {
		// 	}
		// 	@Override
		// 	public void windowLostFocus(WindowEvent a) {
		// 		((JFrame)a.getSource()).dispose();
		// 	}
		// });
    // 
    
    public static String HTMLparser(String response) {
        try {
            // Create a parser
            Parser parser = Parser.builder().build();
            // Create an HTML renderer
            HtmlRenderer renderer = HtmlRenderer.builder().build();
            // Parse the Markdown string
            Node document = parser.parse(response);
            // Render the document to HTML
            String html = renderer.render(document);
            return html;
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Return null in case of any parsing errors
        }
    }

    public static String JSONparser(String jsonResponse) {
        try {
            // Parse the root JSON object
            JSONObject root = new JSONObject(jsonResponse);

            // Navigate to "candidates" array
            JSONArray candidates = root.optJSONArray("candidates");
            if (candidates == null || candidates.isEmpty()) {
                return null; // "candidates" array is missing or empty
            }

            // Get the first object in the "candidates" array
            JSONObject candidate = candidates.getJSONObject(0);

            // Navigate to "content" -> "parts"
            JSONObject content = candidate.optJSONObject("content");
            if (content == null) {
                return null; // "content" object is missing
            }

            JSONArray parts = content.optJSONArray("parts");
            if (parts == null || parts.isEmpty()) {
                return null; // "parts" array is missing or empty
            }

            // Get the first object in the "parts" array
            JSONObject part = parts.getJSONObject(0);

            // Extract and return the "text" field
            return part.optString("text", null); // Returns null if "text" is missing
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Return null in case of any parsing errors
        }
    }

    // qwen 7b kaggle build
    private String sendToApi_local(String userInput) {
        // URL url = new URL ("https://be1e-35-184-23-11.ngrok-free.app");
        
        String prompt = """
        You are an expert about sorting algorithms.
        Your task is answer user's questions about sorting algorithms.
        If the questions is not related to sorting algorithms, you should reply with "I don't know".

        User questions:
        %s
        """.formatted(userInput);
        String escapedPrompt = prompt.replace("\n", "\\n").replace("\"", "\\\"");
        // String payload = "{\"input_text\": \"" + escapedPrompt + "\"}";
        String payload = "{\"input_text\": \"" + escapedPrompt + "\"}";
        String url = "";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(payload))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                String llmResponse = response.body();
                // String final_response = parser(llmResponse);
                return llmResponse;
            } else {
                return "Error: " + response.statusCode();
            }
        } catch (IOException | InterruptedException e) {
            return "Error: " + e.getMessage();
        }
    }

    // gemini
    private String sendToApi(String userInput) {
        // URL url = new URL ("https://be1e-35-184-23-11.ngrok-free.app");
        String key = "AIzaSyABPIPEP8WoVrrjfTtxqjj9JyWl3q_rGDM";
        String prompt = """
        You are an expert about sorting algorithms.
        Your task is answer user's questions about sorting algorithms.
        If the questions is not related to sorting algorithms, you should reply with "I don't know".

        User questions:
        %s
        """.formatted(userInput);
        String escapedPrompt = prompt.replace("\n", "\\n").replace("\"", "\\\"");
        // String payload = "{\"input_text\": \"" + escapedPrompt + "\"}";
        String payload = "{\"contents\":[{\"role\":\"user\", \"parts\":[{\"text\":\"" + escapedPrompt + "\"}]}]}";
        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash-latest:generateContent?key=%s".formatted(key);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(payload))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                String llmResponse = response.body();
                String final_response = JSONparser(llmResponse);
                return final_response;
            } else {
                return "Error: " + response.statusCode();
            }
        } catch (IOException | InterruptedException e) {
            return "Error: " + e.getMessage();
        }
    }

}
