# treasurytakehome-rgb

Overview
The AI‑Powered Alcohol Label Verification App is a Java/Spring Boot prototype designed to help the TTB Label Compliance Division automate routine label checks.
It extracts text from alcohol label images using AI, compares it against application data, and produces clear, human‑reviewable results.
This tool does not auto‑approve labels.
It accelerates routine matching tasks while keeping agents fully in control.
---
🖼️ Architecture Diagram
-
┌──────────────────────────────────────────────────────────────────────────────┐
│                           FRONTEND (Thymeleaf UI)                            │
│                                                                              │
│   • Single-page interface                                                    │
│   • Upload 1–300 label images                                                │
│   • Application data form                                                    │
│   • Results table                                                            │
└──────────────────────────────────────────────────────────────────────────────┘
                                      │
                                      ▼  HTTP (REST)
┌──────────────────────────────────────────────────────────────────────────────┐
│                           SPRING BOOT BACKEND                                │
│                                                                              │
│   ┌──────────────────────────────┐      ┌──────────────────────────────────┐ │
│   │      UploadController        │      │     VerificationController       │ │
│   │  /upload/batch               │      │  /verify                         │ │
│   └──────────────────────────────┘      └──────────────────────────────────┘ │
│                 │                                      │                     │
│                 ▼                                      ▼                     │
│   ┌──────────────────────────────┐      ┌──────────────────────────────────┐ │
│   │   BatchProcessingService     │      │      VerificationService         │ │
│   │  • Parallel processing       │      │  • Fuzzy matching                │ │
│   │  • ExecutorService/WebFlux   │      │  • ABV numeric checks            │ │
│   │  • <5 sec per label          │      │  • Strict warning validation     │ │
│   └──────────────────────────────┘      └──────────────────────────────────┘ │
│                 │                                      │                     │
│                 ▼                                      ▼                     │
│   ┌──────────────────────────────┐      ┌──────────────────────────────────┐ │
│   │     AIExtractionService      │      │         Utility Layer            │ │
│   │  • Calls GPT‑4o/Gemini       │      │  • Fuzzy

---
🎯 Key Features
⚡ Fast Processing (<5 seconds per label)
Agents previously abandoned a scanning pilot that took 30–40 seconds per label.
This prototype is engineered for speed, using parallel processing and async AI calls.
📦 Batch Uploads (1–300 labels)
Supports large importer submissions during peak season.
Agents can upload hundreds of label images at once and receive consolidated results.
🧠 AI‑Driven OCR + Field Extraction
Uses external AI services (GPT‑4o Vision, Gemini Vision, etc.) to extract:
• Brand name
• ABV
• Net contents
• Class/type
• Producer/bottler
• Address
• Country of origin
• Government warning text
🔍 Verification Engine
Compares extracted fields against application data using:
• Fuzzy matching (brand, net contents, class/type)
• Numeric comparison (ABV)
• Strict, case‑sensitive match (government warning)
• Mandatory field checks
Outputs per-field verdicts:
• Pass
• Mismatch
• Missing
• Needs Review
👵 Simple, Accessible UI
Designed for agents with varying tech comfort levels.
One page, large buttons, no hidden menus.
📝 Audit‑Friendly Results
Shows extracted values, expected values, and match reasoning.
Supports human judgment and documentation.
---
🧱 Architecture
Backend
• Java 17
• Spring Boot 3.x
• Spring WebFlux (async AI calls)
• ExecutorService (parallel batch processing)
• Jackson (JSON mapping)
Frontend
• Thymeleaf (simple, accessible UI)
AI Integration
• External AI endpoint (configurable)
• Base64 image upload
• Structured JSON response
Optional Persistence
• PostgreSQL or H2
• Stores audit logs and verification results

📂 Project Structure
src/main/java/org/alchol/validator/govstandards/
│
├── controller/
│   ├── UploadController.java
│   └── VerificationController.java
│
├── service/
│   ├── BatchProcessingService.java
│   ├── AIExtractionService.java
│   └── VerificationService.java
│
├── model/
│   ├── LabelApplication.java
│   ├── ExtractedLabel.java
│   └── VerificationResult.java
│
├── util/
│   ├── FuzzyMatcher.java
│   └── GovernmentWarningValidator.java
│
└── config/
    └── WebClientConfig.java

🚀 Getting Started
Prerequisites
• Java 17
• Maven 3.8+
• Internet access (for AI extraction API)
Run the App
mvn clean install
mvn spring-boot:run

App starts at:
http://localhost:8080

📤 Using the App
1. Upload Labels
• Drag-and-drop or select multiple images
• Supports JPG, PNG, PDF (image-only)
2. Enter Application Data
• Brand
• ABV
• Net contents
• Class/type
• Producer/bottler
• Address
• Country of origin
• Government warning text
3. Process Batch
• Click Process Batch
• Results appear in a table
• Each label shows:
	◦ Extracted fields
	◦ Expected fields
	◦ Match verdicts
	◦ Needs Review flag