from flask import Flask, request, jsonify
import pickle
import os

app = Flask(__name__)

# Check if model files exist
if not os.path.exists('classifier_model.pkl') or not os.path.exists('vectorizer.pkl'):
    print("❌ ERROR: Model files not found!")
    print("   Please run: python train_classifier.py")
    exit(1)

# Load trained model
print("📦 Loading trained model...")
with open('classifier_model.pkl', 'rb') as f:
    classifier = pickle.load(f)

with open('vectorizer.pkl', 'rb') as f:
    vectorizer = pickle.load(f)

print("✅ Model loaded successfully!")

@app.route('/', methods=['GET'])
def home():
    return jsonify({
        'status': 'running',
        'message': 'ML Classification Service is active',
        'endpoint': '/classify',
        'method': 'POST'
    })

@app.route('/classify', methods=['POST'])
def classify():
    try:
        data = request.get_json()
        
        if not data or 'text' not in data:
            return jsonify({
                'error': 'Missing "text" field in request body'
            }), 400
        
        text = data.get('text', '').strip()
        
        if not text:
            return jsonify({
                'error': 'Text cannot be empty'
            }), 400
        
        # Transform and predict
        X_test = vectorizer.transform([text])
        prediction = classifier.predict(X_test)[0]
        probabilities = classifier.predict_proba(X_test)[0]
        confidence = float(max(probabilities))
        
        # Log the prediction
        print(f"📝 Classified: '{text[:50]}...' → {prediction} (confidence: {confidence:.2%})")
        
        return jsonify({
            'domain': prediction,
            'confidence': confidence,
            'text': text
        })
        
    except Exception as e:
        print(f"❌ Error: {str(e)}")
        return jsonify({
            'error': str(e)
        }), 500

@app.route('/health', methods=['GET'])
def health():
    return jsonify({
        'status': 'healthy',
        'model_loaded': True
    })

if __name__ == '__main__':
    print("\n" + "="*60)
    print("🤖 ML CLASSIFICATION SERVICE")
    print("="*60)
    print("✅ Running with trained model")
    print("✅ Endpoint: http://localhost:5000/classify")
    print("✅ Method: POST")
    print("✅ Body: {\"text\": \"your complaint text\"}")
    print("="*60 + "\n")
    
    app.run(host='0.0.0.0', port=5000, debug=True)
