import SwiftUI
import shared

struct ContentView: View {
    @ObservedObject private(set) var viewModel: ViewModel
    
    var body: some View {
        encView()
    }
    
    private func encView() -> Text {
        switch viewModel.enc {
        case .loading:
            return Text("Loading...")
        case .success(let enc):
            let text = "#\(enc.number)\n\n\(enc.body)"
            return Text(text)
        case .error:
            return Text("Error occurred")
        }
    }
}

extension ContentView {
    class ViewModel: ObservableObject {
        enum LoadableEnc {
            case loading
            case success(Encouragement)
            case error
        }
        
        @Published var enc = LoadableEnc.loading
        
        init() {
            loadEnc()
        }
        
        func loadEnc() {
            self.enc = .loading
            SharedMain.init().latestEnc { enc, error in
                if let enc = enc {
                    self.enc = .success(enc)
                } else {
                    self.enc = .error
                }
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView(viewModel: .init())
    }
}
