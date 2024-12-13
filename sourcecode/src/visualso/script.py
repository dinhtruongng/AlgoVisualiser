import assemblyai as aai
import re
import sys

aai.settings.api_key = "b0e2bed4e01f48eea5739561efcb3d22"

transcriber = aai.Transcriber()

def process(file_path):
    # You can use a local filepath:
    audio_file = file_path


    config = aai.TranscriptionConfig(speaker_labels=True)

    transcript = transcriber.transcribe(audio_file, config)

    if transcript.status == aai.TranscriptStatus.error:
        print(f"Transcription failed: {transcript.error}")
        exit(1)
    sentence = transcript.text

    # Step 1: Normalization
    sentence = sentence.lower()
    sentence = re.sub(r'[^\w\s]', '', sentence)  # Remove punctuation
    sentence = sentence.replace(',', '')
    # Step 2: Tokenization
    tokens = sentence.split()

    # Homophones of 'sort'
    homophones = {'sort', 'short', 'sought', 'sorte', 'sourt', 'soart', 'saught', 'shot', "shaw", "sought"}

    # Number words mapping
    num_words = {
        'zero': 0, 'one': 1, 'two': 2, 'three': 3, 'four': 4, 'five': 5,
        'six': 6, 'seven': 7, 'eight': 8, 'nine': 9, 'ten': 10,
        'eleven': 11, 'twelve': 12, 'thirteen': 13, 'fourteen': 14,
        'fifteen': 15, 'sixteen': 16, 'seventeen': 17, 'eighteen': 18,
        'nineteen': 19, 'twenty': 20, 'thirty': 30, 'forty': 40,
        'fifty': 50, 'sixty': 60, 'seventy': 70, 'eighty': 80,
        'ninety': 90, 'hundred': 100, 'thousand': 1000,
        'million': 1000000, 'billion': 1000000000
    }

    # Step 3: Identify the sort word and its predecessor
    sort_word = ''
    for i in range(1, len(tokens)):
        if tokens[i] in homophones:
            sort_word = tokens[i-1] + 'sort'
            break

    # Step 4: Extract numbers
    numbers = []
    i = 0
    while i < len(tokens):
        if tokens[i].isdigit():
            # Directly convert digits to int
            numbers.append(int(tokens[i]))
            i += 1
        elif tokens[i] in num_words:
            # Start building the number from words
            num = 0
            while i < len(tokens) and tokens[i] in num_words:
                word = tokens[i]
                if word == 'hundred':
                    num *= num_words[word]
                elif word in ('thousand', 'million', 'billion'):
                    num *= num_words[word]
                else:
                    numbers.append(num_words[tokens[i]])
                i += 1
            numbers.append(num)
        else:
            i += 1
    t = sort_word
    if t[0] == 'm':
        t = 'Merge Sort_voice'
    if t[0] == 'b':
        t = 'Bubble Sort_voice'
    if t[0] == 'i':
        t = 'Insertion Sort_voice'
    if t[0] == 'q':
        t = 'Quick Sort_voice'
    if t[0] == 'r':
        t = 'Radix Sort_voice'
    if t[0] == 'c':
        t = 'Counting Sort_voice'
    if t == 'shellsort':
        t = 'Shell Sort_voice'
    if t == 'selectionsort':
        t = 'Selection Sort_voice'

    numbers = [x for x in numbers if x!= 0]
    return t, numbers

if len(sys.argv) > 1:
    wav_file_path = sys.argv[1]
    sort_word, numbers = process(wav_file_path)
    print(sort_word)
    print(numbers)
else:
    print("No input WAV file path provided.")


