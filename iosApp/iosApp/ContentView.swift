import SwiftUI
import shared

struct ContentView: View {
    @ObservedObject private(set) var viewModel: ViewModel
    
    var body: some View {
        messageView()
    }
    
    private func messageView() -> Text {
        switch viewModel.message {
        case .loading:
            return Text("Loading...")
        case .success(let message):
            return Text(message)
        case .error:
            return Text("Error occurred")
        }
    }
}

extension ContentView {
    class ViewModel: ObservableObject {
        enum LoadableGreeting {
            case loading
            case success(String)
            case error
        }
        
        let greeting: Greeting
        @Published var message = LoadableGreeting.loading
        
        init(greeting: Greeting) {
            self.greeting = greeting
            loadMessage()
        }
        
        func loadMessage() {
            self.message = .loading
            greeting.greeting { message, error in
                if let message = message {
                    self.message = .success(message)
                } else {
                    self.message = .error
                }
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView(viewModel: .init(greeting: Greeting()))
    }
}
