import pandas as pd
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.naive_bayes import MultinomialNB
from sklearn.model_selection import train_test_split
from sklearn.metrics import classification_report
import pickle

print("🚀 Starting ML Classifier Training...")

# Load the dataset
df = pd.read_csv('expanded_feedback_dataset_1000.csv')

print(f"✅ Loaded {len(df)} entries")
print(f"✅ {df['Domain'].nunique()} unique domains")
print(f"✅ Domains: {df['Domain'].unique()[:10]}...")

# Split data
X = df['Problem Description']
y = df['Domain']

X_train, X_test, y_train, y_test = train_test_split(
    X, y, test_size=0.2, random_state=42, stratify=y
)

print(f"\n📊 Training set: {len(X_train)} samples")
print(f"📊 Test set: {len(X_test)} samples")

# Train vectorizer and model
print("\n🧠 Training TF-IDF Vectorizer...")
vectorizer = TfidfVectorizer(
    max_features=1000,
    ngram_range=(1, 2),
    min_df=2
)
X_train_vec = vectorizer.fit_transform(X_train)
X_test_vec = vectorizer.transform(X_test)

print("🧠 Training Naive Bayes Classifier...")
classifier = MultinomialNB(alpha=0.1)
classifier.fit(X_train_vec, y_train)

# Test accuracy
accuracy = classifier.score(X_test_vec, y_test)
print(f"\n✅ Model accuracy: {accuracy * 100:.2f}%")

# Detailed classification report
y_pred = classifier.predict(X_test_vec)
print("\n📈 Classification Report:")
print(classification_report(y_test, y_pred, zero_division=0))

# Save model and vectorizer
print("\n💾 Saving model files...")
with open('classifier_model.pkl', 'wb') as f:
    pickle.dump(classifier, f)

with open('vectorizer.pkl', 'wb') as f:
    pickle.dump(vectorizer, f)

print("✅ Model and vectorizer saved successfully!")
print("✅ Files created: classifier_model.pkl, vectorizer.pkl")

# Test with sample predictions
print("\n🧪 Testing with sample predictions:")
test_samples = [
    "Water leaking from pipe on main street",
    "Garbage not collected for 3 days",
    "Streetlight not working at night",
    "Road full of potholes"
]

for sample in test_samples:
    vec = vectorizer.transform([sample])
    pred = classifier.predict(vec)[0]
    print(f"   '{sample}' → {pred}")

print("\n🎉 Training complete! You can now run ml_service.py")
