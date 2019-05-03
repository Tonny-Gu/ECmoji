# -*- coding: utf-8 -*-

""" Use DeepMoji to score texts for emoji distribution.

The resulting emoji ids (0-63) correspond to the mapping
in emoji_overview.png file at the root of the DeepMoji repo.

Writes the result to a csv file.
"""
from __future__ import print_function, division
import example_helper
import json
import csv
import numpy as np
from deepmoji.sentence_tokenizer import SentenceTokenizer
from deepmoji.model_def import deepmoji_emojis
from deepmoji.global_variables import PRETRAINED_PATH, VOCAB_PATH




def top_elements(array, k):
    ind = np.argpartition(array, -k)[-k:]
    return ind[np.argsort(array[ind])][::-1]

def scoreTexts(TEST_SENTENCES):
    maxlen = 30
    batch_size = 32

    with open(VOCAB_PATH, 'r') as f:
        vocabulary = json.load(f)
    st = SentenceTokenizer(vocabulary, maxlen)
    tokenized, _, _ = st.tokenize_sentences(TEST_SENTENCES)

    model = deepmoji_emojis(maxlen, PRETRAINED_PATH)
    model.summary()

    prob = model.predict(tokenized)

    # Find top emojis for each sentence. Emoji ids (0-63)
    # correspond to the mapping in emoji_overview.png
    # at the root of the DeepMoji repo.
    scores = []
    for i, t in enumerate(TEST_SENTENCES):
        t_tokens = tokenized[i]
        t_score = {}
        t_score["text"] = t
        t_prob = prob[i]
        ind_top = top_elements(t_prob, 5)
        t_score["prob"]=sum(t_prob[ind_top])

        emoji_score = {}
        for ind in ind_top:
            emoji_score[ind] = t_prob[ind]
        t_score["score"]=emoji_score
        scores.append(t_score)
    return scores

"""
['Text', 'Top5%',
'Emoji_1', 'Emoji_2', 'Emoji_3', 'Emoji_4', 'Emoji_5',
'Pct_1', 'Pct_2', 'Pct_3', 'Pct_4', 'Pct_5']
"""